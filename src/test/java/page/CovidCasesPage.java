package page;

import com.codeborne.selenide.SelenideElement;

import org.apache.commons.collections4.ListUtils;
import utils.SendToTelegram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$$x;

public class CovidCasesPage {
    private static CovidCasesPage covidCases = new CovidCasesPage();
    private SendToTelegram send = new SendToTelegram();

    private CovidCasesPage() { }

    public static CovidCasesPage getCovidCases() {
        return covidCases;
    }

    public void getDataFromWiki() throws IOException, InterruptedException {
        List<String> newList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add("Covid cases in Malaysia : ");
        String dateAndCase = "";
        List<SelenideElement> dateElements = $$x("//tr[@class='mw-collapsible mw-made-collapsible']/td[1]");
        for(SelenideElement dateElement : dateElements) {
            String date = dateElement.text();
            String c_19Case = dateElement.find(byXpath(".//following-sibling::td[2]/span[2]")).text()
                    .replace("(+","")
                    .replace(",","")
                    .replace(")","");
            //dates.add(date);
            //cases.add(c_19Case);
            dateAndCase = date + " : " + c_19Case + " cases";
            newList.add(dateAndCase);
        }
        List<String> result = ListUtils.union(list,newList);
        send.sendToTelegram(result);
        result.forEach(System.out::println);
    }
}
