package br.ce.wcaquino.tasks.functional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	
	private WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		
		// Esperando a tela carregar
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		
		// Abrir a tela inicial
		WebDriver driver = acessarAplicacao();
		
		try {
			// Clicar no botão addTodo
			driver.findElement(By.id("addTodo")).click();

			// Preencher a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			// Preencher a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			// Clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// Validando o retorno
			String mensagemAtual = driver.findElement(By.id("message")).getText();
			assertEquals("Success!", mensagemAtual);
			
		} finally {
			// Fechar o browser no final
			driver.quit();
		}
	}

	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		
		// Abrir a tela inicial
		WebDriver driver = acessarAplicacao();
		
		try {
			// Clicar no botão addTodo
			driver.findElement(By.id("addTodo")).click();

			// Preencher a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			// Clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// Validando o retorno
			String mensagemAtual = driver.findElement(By.id("message")).getText();
			assertEquals("Fill the task description", mensagemAtual);
			
		} finally {
			// Fechar o browser no final
			driver.quit();
		}
	}

	@Test
	public void naoDeveSalvarTarefaSemData() {
		
		// Abrir a tela inicial
		WebDriver driver = acessarAplicacao();
		
		try {
			// Clicar no botão addTodo
			driver.findElement(By.id("addTodo")).click();

			// Preencher a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			// Clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// Validando o retorno
			String mensagemAtual = driver.findElement(By.id("message")).getText();
			assertEquals("Fill the due date", mensagemAtual);
			
		} finally {
			// Fechar o browser no final
			driver.quit();
		}
	}

	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		
		// Abrir a tela inicial
		WebDriver driver = acessarAplicacao();
		
		try {
			// Clicar no botão addTodo
			driver.findElement(By.id("addTodo")).click();

			// Preencher a descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			// Preencher a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");
			
			// Clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// Validando o retorno
			String mensagemAtual = driver.findElement(By.id("message")).getText();
			assertEquals("Due date must not be in past", mensagemAtual);
			
		} finally {
			// Fechar o browser no final
			driver.quit();
		}
	}

}
