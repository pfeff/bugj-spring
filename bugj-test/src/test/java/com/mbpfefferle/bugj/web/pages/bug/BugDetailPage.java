package com.mbpfefferle.bugj.web.pages.bug;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BugDetailPage {

    @FindBy(xpath="//p[@id='synopsis']")
    private WebElement synopsis;

    public String getSynopsis() {
        return synopsis.getText();
    }
}

