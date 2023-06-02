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

	// login et/ou mot de passe vide

	@Given("Je me rends sur le site {string}")
	public void jeMeRendsSurLeSite(String url) {
		tasksPage.ConnectURL();
	}

	@When("les champs login et/ou mot de passe sont vides")
	public void jeVideChamps() {
		String login=tasksPage.textLogin.getText();
		String pwd=tasksPage.textPassword.getText();
		if (login.length()==0 && pwd.length()!=0)
		{
			tasksPage.textLogin.clear();
		}
		else if (login.length()!=0 && pwd.length()==0)
		{
			tasksPage.textPassword.clear();
		}
		else 
		{
			tasksPage.textLogin.clear();
			tasksPage.textPassword.clear();
		}
	}

	@Then("Je vérifie que le bouton de connexion est non cliquable")
	public void jeVérifieQueLeBoutonDeConnexionEstNonCliquable() {
		Boolean etatBtn = tasksPage.loginBtn.isEnabled();
		Assert.assertFalse(etatBtn);
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
	
	@Then("les liens {string} {string} ne sont pas affichés")
	public void jeVérifieLienDisplayed() {
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
	
	@Then("les liens {string} {string} sont affichés")
	public void jeVérifieQueLesLiensSontAffiches(String linkTasks, String linkLogout) {
		String link_tasks = TasksPage.lienTasks.getText();
		String link_logout = TasksPage.lienDeconnexion.getText();
		Assert.assertEquals(link_tasks,linkTasks);
		Assert.assertEquals(link_logout,linkLogout);
	}
}
