package com.project.Bookmyshow.Transformer;

import com.project.Bookmyshow.Dto.TheatreDto;
import com.project.Bookmyshow.Module.Theatre;

public class TheatreTransformer {
    public static Theatre TheatreDtoToEntity(TheatreDto theatreDto)
    {
        Theatre theatre = Theatre.builder().name(theatreDto.getName()).location(theatreDto.getLocation()).
                build();
        return theatre;
    }
}
