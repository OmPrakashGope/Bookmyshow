package com.project.Bookmyshow.Transformer;
import com.project.Bookmyshow.Dto.RequestDtos.ShowDto;
import com.project.Bookmyshow.Module.Show;

public class ShowTransformer {
    public static Show showDtoToEntity(ShowDto showDto)
    {
        Show show = Show.builder().startingTime(showDto.getStartingTime()).
                date(showDto.getDate()).build();
        return show;
    }
}
