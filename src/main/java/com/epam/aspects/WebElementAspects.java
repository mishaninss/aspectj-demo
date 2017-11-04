package com.epam.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.selenium.factory.WebDriverPool;

@Aspect
public class WebElementAspects {
	
	@Pointcut("call(* org.openqa.selenium.WebElement.* (..))")
	public void anyWebElementMethod() {}

	@Before("anyWebElementMethod()")
	public void highlightElement(JoinPoint joinPoint) {
        unhighlightElement();
	    WebElement webElement = (WebElement) joinPoint.getTarget();
        highlightElement(webElement);
    }

    private void highlightElement(WebElement webElement){
        WebDriver webDriver = WebDriverPool.DEFAULT.getDriver("chrome");
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].style.border='5px solid red'; arguments[0].classList.add('wbBorder');", webElement);
    }

    private void unhighlightElement(){
        WebDriver webDriver = WebDriverPool.DEFAULT.getDriver("chrome");
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript(
        "var elements = document.getElementsByClassName('wbBorder');" +
               "if (elements.length > 0){" +
                    "elements[0].style.border='';" +
                    "elements[0].classList.remove('wbBorder');" +
                "}");
    }

}
