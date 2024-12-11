package com.softserve.ui

import io.github.bonigarcia.wdm.WebDriverManager
import org.junit.jupiter.api.*
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.io.IOException
import java.time.Duration


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GreencityLoginTest {
    private val BASE_URL = "https://www.greencity.cx.ua/#/ubs"
    private val IMPLICITLY_WAIT_SECONDS = 10L
    private var driver: WebDriver? = null

    @BeforeAll
    @Throws(IOException::class)
    fun setup() {
        WebDriverManager.chromedriver().setup()
        driver = ChromeDriver()
        //
        driver!!.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS)) // 0 by default
        driver!!.manage().window().maximize()
        println("@BeforeAll executed")
    }

    @AfterAll
    fun tear() {
        if (driver != null) {
            driver!!.quit() // close()
        }
        println("@AfterAll executed")
    }

    @BeforeEach
    @Throws(InterruptedException::class)
    fun setupThis() {
        driver!![BASE_URL]
        Thread.sleep(2000) // For Presentation
        println("\t@BeforeEach executed")
    }

    @AfterEach
    @Throws(InterruptedException::class)
    fun tearThis() {
        // Sign out
        // Clear session
        Thread.sleep(4000) // For Presentation
        println("\t@AfterEach executed")
    }

    @Test
    @Throws(InterruptedException::class, IOException::class)
    fun checkSignIn() {
        println("\t\tStarting checkSignIn() ...")
        //
        driver!!.findElement(By.cssSelector("app-ubs .ubs-header-sing-in-img.ng-star-inserted")).click()
        Thread.sleep(1000) // For Presentation
        //
        driver!!.findElement(By.id("email")).click()
        driver!!.findElement(By.id("email")).clear()
        driver!!.findElement(By.id("email")).sendKeys("abcd#inohm.com")
        Thread.sleep(1000) // For Presentation
        //
        driver!!.findElement(By.id("password")).click()
        driver!!.findElement(By.id("password")).clear()
        driver!!.findElement(By.id("password")).sendKeys("Qwerty_0")
        Thread.sleep(2000) // For Presentation
        //
        val expected = "Please check if the email is written correctly"
        val actual = driver!!.findElement(By.cssSelector("div.validation-email-error app-error"))
        Assertions.assertTrue(actual.text.contains(expected))
        //
        // TODO
    }
}
