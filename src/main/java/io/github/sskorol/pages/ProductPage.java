package io.github.sskorol.pages;

import io.github.sskorol.core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static io.github.sskorol.core.BaseConfig.BASE_CONFIG;

@SuppressWarnings("JavadocType")
public class ProductPage extends BasePage {

    private static final String REGEXP_FOR_COLOR = ".colorsprite.*(\\n*\\t*.*).a-size-small a-color-base.>(.*?)<";
    private static final String PURCHASE_STATUS = "Operation was successfully completed";
    private static final String ATTR_OF_SCENTS_LIST = "alt";
    private final By checkboxes = By.xpath("//li/span/span/div/label/span/span");
    private final By resultProducts = By.xpath("//ul[@id='s-results-list-atf']/li");
    private final By colors = By.cssSelector(".colorsprite");
    private final By scents = By.xpath("(//img[@id=''])");
    private final By getAllScentsButton = By.id("expanderButton_scent_name");
    private final By buyButton = By.id("buy");
    private final By sortByDiv = By.xpath("//form[@id='searchSortForm']/select/option");

    @Step("Select the \"{color}\" color.")
    public ProductPage selectByColor(final String color) {
        selectColor(colors, REGEXP_FOR_COLOR, color);
        return this;
    }

    @Step("Select the \"{condition}\" checkbox.")
    public ProductPage selectCheckboxBy(final String condition) {
        selectByParameters(checkboxes, condition);
        return this;
    }

    @Step("Select the product")
    public ProductPage selectProduct() {
        selectProduct(resultProducts);
        return this;
    }

    @Step("Select the \"{category}\" category.")
    public ProductPage selectCategoryBy(final String category) {
        selectCategory(category);
        return this;
    }

    @Step("Select the \"{value}\" scent.")
    public ProductPage selectScent(final String value) {
        click(getAllScentsButton);
        selectByAttribute(scents, value, ATTR_OF_SCENTS_LIST);
        return this;
    }

    @Step("Buy the product")
    public ProductPage buy() {
        phantomClick(buyButton);
        return this;
    }

    @Step("Sort by: \"{value}\".")
    public ProductPage sortBy(final String value) {
        selectByParameters(sortByDiv, value);
        return this;
    }

    public String getPurchaseStatus() {
        return getPhantomText(buyButton, PURCHASE_STATUS);
    }

    @Override
    public String url() {
        return BASE_CONFIG.url();
    }
}
