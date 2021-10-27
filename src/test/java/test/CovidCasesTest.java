package test;

import org.testng.annotations.Test;
import page.CovidCasesPage;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

public class CovidCasesTest {
    private CovidCasesPage data = CovidCasesPage.getCovidCases();

    @Test
    public void AC_1() throws IOException, InterruptedException {
        open("https://en.wikipedia.org/wiki/COVID-19_pandemic_in_Malaysia");
        data.getDataFromWiki();
    }
}
