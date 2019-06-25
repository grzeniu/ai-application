package pl.edu.wat.ai.app.mappers;

import pl.edu.wat.ai.app.interfaces.rest.user.finances.FinanceDto;
import pl.edu.wat.ai.app.interfaces.rest.user.finances.FinanceResponseDto;
import pl.edu.wat.ai.app.user.finances.Finance;

public class FinancesMapperImpl implements FinancesMapper {
    @Override
    public FinanceDto financeToDto(Finance finance) {
        FinanceDto financeDto = new FinanceDto();

        financeDto.setDescription(finance.getDescription());
        financeDto.setValue(finance.getValue());
        financeDto.setCategoryId(finance.getId());
        return financeDto;
    }

    @Override
    public FinanceResponseDto financeToResponseDto(Finance finance) {

        FinanceResponseDto financeResponseDto = new FinanceResponseDto();

        financeResponseDto.setId(finance.getId());
        financeResponseDto.setDescription(finance.getDescription());
        financeResponseDto.setValue(finance.getValue());
        financeResponseDto.setFinanceType(finance.getFinanceType());
        financeResponseDto.setCreatedBy(finance.getCreatedBy());
        financeResponseDto.setCreatedDate(finance.getCreatedDate());
        financeResponseDto.setCategory(CategoryMapper.INSTANCE.categoryToEngDto(finance.getCategory()));

        return financeResponseDto;
    }
}
