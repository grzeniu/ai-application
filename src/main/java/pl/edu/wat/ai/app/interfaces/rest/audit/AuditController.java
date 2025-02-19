package pl.edu.wat.ai.app.interfaces.rest.audit;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.ai.app.user.finances.FinanceService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/reports")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuditController {

    private final FinanceService financeService;

    @RequestMapping(value = "/pl", produces = "text/csv")
    public void generatePLRaport(HttpServletResponse response, Principal principal) throws IOException {
        final LocalDate date = LocalDate.now();

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=raport-" + date.getMonthValue() + "." + date.getYear() + ".csv");
        WriteDataToCSV.writeObjectToPLCSV(response.getWriter(), financeService.getFinanceByUserByDate(principal.getName(), date.getMonthValue(), date.getYear()));
    }

    @RequestMapping(value = "/eng", produces = "text/csv")
    public void generateENGRaport(HttpServletResponse response, Principal principal) throws IOException {
        final LocalDate date = LocalDate.now();

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=raport-" + date.getMonthValue() + "." + date.getYear() + ".csv");
        WriteDataToCSV.writeObjectToENGCSV(response.getWriter(), financeService.getFinanceByUserByDate(principal.getName(), date.getMonthValue(), date.getYear()));
    }
}
