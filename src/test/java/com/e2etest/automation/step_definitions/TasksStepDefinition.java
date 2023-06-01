package com.e2etest.automation.step_definitions;

import org.junit.Assert;

import com.e2etest.automation.page_objects.TasksPage;
import com.e2etest.automation.utils.SeleniumUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TasksStepDefinition {

	public SeleniumUtils seleniumUtils;
	public TasksPage tasksPage;

	public TasksStepDefinition() {
		tasksPage = new TasksPage();
		seleniumUtils = new SeleniumUtils();
	}

	// login et mot de passe vide

	@Given("Je me rends sur le site {string}")
	public void jeMeRendsSurLeSite(String url) {
		tasksPage.ConnectURL();
	}

	@When("les champs login et mot de passe sont vides")
	public void jeVideChamps() {
		tasksPage.textLogin.clear();
		tasksPage.textPassword.clear();
	}

	@Then("Je vérifie que le bouton de connexion est non cliquable")
	public void jeVérifieQueLeBoutonDeConnexionEstNonCliquable() {
		Boolean etatBtn = tasksPage.loginBtn.isEnabled();
		Assert.assertFalse(etatBtn);
	}

	// Saisir login mais mot de passe vide
	@When("Je saisie le login {string} et je vide le mot de passe")
	public void jeVidePWDNotLogin(String log) {
		tasksPage.textLogin.sendKeys(log);
		tasksPage.textPassword.clear();
	}

	// Vider le login mais je saisi le mot de passe
	@When("Je vide le login et je  saisie le mot de passe {string}")
	public void jeVideLoginNotPWD(String PWD) {
		tasksPage.textLogin.clear();
		tasksPage.textPassword.sendKeys(PWD);
	}

	// Se connecter avec Login et PWD non valides
	@When("Je saisie le login {string} et le mot de passe {string}")
	public void jeSaisieLoginAndPWDnonValide(String log, String Pwd) {
		tasksPage.textLogin.sendKeys(log);
		tasksPage.textPassword.sendKeys(Pwd);
	}

	@When("Je clique sur le bouton de connexion")
	public void jeCliqueSurLeBoutonDeConnexion() {
		TasksPage.loginBtn.click();
	}

	@Then("un message rouge apparait pour alerter {string}")
	public void jeVérifieQuUnMessageRougeApparaitPourAlerter(String string) {
		boolean showMsg = seleniumUtils.isElementDisplayed(tasksPage.alertMessage);
		Assert.assertTrue(showMsg);
	}

	// Se connecter avec Login et PWD valides
	@When("Je me connecte")
	public void jeSaisieLoginAndPWDValide() {
		tasksPage.login();
	}

	@Then("la page change")
	public void LaPageChange() {
		tasksPage.checkUrlChanged();
	}
}
