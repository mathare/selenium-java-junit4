package com.the_internet.herokuapp.pages;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.the_internet.herokuapp.pages.locators.HomePageLocators.*;

public class HomePage extends BasePage {

    public int getNumSubPages() {
        return getAllSubPageListElements().size();
    }

    public List<String> getSubPageNames() {
        List<String> subPageNames = new ArrayList<>();
        for (WebElement subPage : getAllSubPageListElements()) {
            // Where a list element includes additional details in brackets e.g. login credentials exclude these from the page name
            subPageNames.add(subPage.getText().split(" \\(")[0]);
        }
        return subPageNames;
    }

    public void clickOnPageLink(String pageName) {
        for (WebElement subPage : getAllSubPageLinks()) {
            if (subPage.getText().startsWith(pageName)) {
                subPage.click();
                break;
            }
        }
    }
}
