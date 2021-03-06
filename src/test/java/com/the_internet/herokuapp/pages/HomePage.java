package com.the_internet.herokuapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {

    private static final By pageTitleLocator = By.tagName("h1");
    private static final By subheaderLocator = By.tagName("h2");
    private static final By subPageListLocator = By.xpath("//*[@id=\"content\"]/ul");
    private static final By listElementLocator = By.tagName("li");

    private WebElement getPageTitle() {
        return driver.findElement(pageTitleLocator);
    }

    private WebElement getSubheader() {
        return driver.findElement(subheaderLocator);
    }

    private WebElement getSubPageList() {
        return driver.findElement(subPageListLocator);
    }

    private List<WebElement> getAllSubPageListElements() {
        return getSubPageList().findElements(listElementLocator);
    }

    private List<WebElement> getAllSubPageLinks() {
        List<WebElement> pageList = getAllSubPageListElements();
        List<WebElement> pageLinks = new ArrayList<>();
        for (WebElement page : pageList) {
            pageLinks.add(page.findElement(anchorLocator));
        }
        return pageLinks;
    }

    @Override
    public String getPageTitleText() {
        return getPageTitle().getText();
    }

    public String getSubheaderText() {
        return getSubheader().getText();
    }

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
