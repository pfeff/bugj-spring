package com.mbpfefferle.bugj.web.pages.bug;

import static org.jboss.arquillian.graphene.Graphene.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewBugPage {

    @FindBy(xpath="//input[@name='synopsis']")
    private WebElement synopsis;

    @FindBy(xpath="//input[@name='details']")
    private WebElement details;

    @FindBy(xpath="//input[@name='steps']")
    private WebElement steps;

    @FindBy(xpath="//button[span='Submit']")
    private WebElement button;

    public String getSynopsys() {
        return synopsis.getText();
    }

    public void setSynopsis(String value) {
        synopsis.sendKeys(value);
    }

    public void submit() {
        button.submit();
    }
}

