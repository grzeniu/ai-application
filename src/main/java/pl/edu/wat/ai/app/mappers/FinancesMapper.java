package pl.edu.wat.ai.app.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.edu.wat.ai.app.interfaces.rest.user.finances.FinanceDto;
import pl.edu.wat.ai.app.interfaces.rest.user.finances.FinanceResponseDto;
import pl.edu.wat.ai.app.user.finances.Finance;

@Mapper(uses = CategoryMapper.class)
public interface FinancesMapper {

    FinancesMapper INSTANCE = Mappers.getMapper(FinancesMapper.class);
    @Mapping(target = "categoryId", source = "fincance")
    FinanceDto financeToDto(Finance finance);

    FinanceResponseDto financeToResponseDto(Finance finance);

}
