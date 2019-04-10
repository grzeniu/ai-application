package pl.edu.wat.ai.app.interfaces.rest.audit;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import pl.edu.wat.ai.app.finances.Finance;

import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Slf4j
class WriteDataToCSV {


    static void writeObjectToPLCSV(PrintWriter writer, List<Finance> finances) {
        try (
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("Opis", "Wartosc","Typ","Wlasciciel","Data dodania","Kategoria"));
        ) {
            for (Finance finance : finances) {
                List<String> data = Arrays.asList(
                        finance.getDescription(),
                        finance.getValue(),
                        getPolishName(finance.getFinanceType()),
                        finance.getCreatedBy(),
                        finance.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        finance.getCategory().getPolishName()
                );

                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
        } catch (Exception e) {
            log.error("Writing CSV error!");
        }
    }

    static void writeObjectToENGCSV(PrintWriter writer, List<Finance> finances) {
        try (
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("Description", "Value","Type","Owner","Created date","Category"));
        ) {
            for (Finance finance : finances) {
                List<String> data = Arrays.asList(
                        finance.getDescription(),
                        finance.getValue(),
                        finance.getFinanceType(),
                        finance.getCreatedBy(),
                        finance.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                        finance.getCategory().getEnglishName()
                );

                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
        } catch (Exception e) {
            log.error("Writing CSV error!");
        }
    }

    private static String getPolishName(String financeType){
        if(financeType.equals("INCOME"))
            return "PRZYCHOD";
        else
            return "WYDATEK";
    }
}
