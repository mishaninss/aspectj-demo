package com.epam.aspects;

import com.epam.utils.cucumber.DataProvider;
import gherkin.formatter.model.Examples;
import gherkin.formatter.model.ExamplesTableRow;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
@Aspect
public class CucumberAspects {

	@Pointcut("call(* cucumber.runtime.model.CucumberScenarioOutline.examples (..))")
	public void pointcutCucumberScenarioOutlineExamplesCall() {
	    //Declaration of a pointcut for call to CucumberScenarioOutline.examples method
    }

	@Around(value="pointcutCucumberScenarioOutlineExamplesCall()")
	public Object adviceAroundSeleniumCall(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args.length > 0){
            Examples examples = (Examples) args[0];
            String description = examples.getDescription().trim();
            if (description.toLowerCase().contains("dataprovider:")){
                String dataProviderClassName = description.substring(description.indexOf(':')+ 1).trim();
                if (StringUtils.isNoneBlank(dataProviderClassName)) {
                    Class<?> clazz = Class.forName(dataProviderClassName);
                    Object inst = clazz.newInstance();
                    if (inst instanceof DataProvider) {
                        DataProvider dp = (DataProvider) inst;
                        String[][] data = dp.getData(examples);
                        List<ExamplesTableRow> rows = examples.getRows();
                        if (!rows.isEmpty()) {
                            ExamplesTableRow header = rows.get(0);
                            rows.clear();
                            rows.add(header);
                        } else {
                            rows.clear();
                        }
                        for (String[] dataRow : data) {
                            List<String> cells = new ArrayList<>();
                            cells.addAll(Arrays.asList(dataRow));
                            ExamplesTableRow row
                                    = new ExamplesTableRow(new ArrayList<>(), cells, 1, UUID.randomUUID().toString());
                            rows.add(row);
                        }
                        examples.setRows(rows);
                        joinPoint.proceed(new Object[]{examples});
                    }
                }
            } else {
                joinPoint.proceed();
            }
        }
        return null;
    }
}
