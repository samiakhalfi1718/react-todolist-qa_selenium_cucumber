package com.e2etest.automation.page_objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.e2etest.automation.utils.ConfigFileReader;
import com.e2etest.automation.utils.SeleniumUtils;
import com.e2etest.automation.utils.Setup;

public class TasksPage {

	public ConfigFileReader configFileReader;
	public SeleniumUtils seleniumUtils;
	
	final static String LOGIN = "//input[@type='email']";
	final static String PASSWORD = "//input[@type='password']";
	final static String ALERT_MSG= "//*[@class='alert alert-danger']";
	final static String BUTTON_CONNEXION = "//*[@type='submit']";
	
	/* @FindBy */
	@FindBy(how = How.XPATH, using = LOGIN)
	public static WebElement textLogin;
	@FindBy(how = How.XPATH, using = PASSWORD)
	public static WebElement textPassword;
	@FindBy(how = How.XPATH, using = BUTTON_CONNEXION)
	public static WebElement loginBtn;
	@FindBy(how = How.XPATH, using = ALERT_MSG)
	public static WebElement alertMessage;
	
	public TasksPage() {
		configFileReader = new ConfigFileReader();
		PageFactory.initElements(Setup.getDriver(), this);
		seleniumUtils = new SeleniumUtils();
	}
	
	public void ConnectURL() {
		seleniumUtils.get(configFileReader.getProperties("todolist.url"));
	}
	public void login() {
		textLogin.clear();
		textLogin.sendKeys(configFileReader.getProperties("todolist.login"));
		textPassword.clear();
		textPassword.sendKeys(configFileReader.getProperties("todolist.password"));
		loginBtn.click();
	}
	public void checkUrlChanged() {
		String actualUrl = Setup.getDriver().getCurrentUrl();
		System.out.println("Title of page is " + actualUrl);
		String url = configFileReader.getProperties("todolist.url");
		if (actualUrl.equals(url)) {
			System.out.println("Failed - The URL is not changed");
		} else {
			System.out.println("Succesfull - The URL is changed");
		}
	}
}
