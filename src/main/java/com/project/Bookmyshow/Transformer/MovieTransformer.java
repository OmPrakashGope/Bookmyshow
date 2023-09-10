package com.project.Bookmyshow.Transformer;

import com.project.Bookmyshow.Dto.RequestDtos.AddMovieDto;
import com.project.Bookmyshow.Module.Movie;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
//@AllArgsConstructor
public class MovieTransformer {
    public static Movie movieDtoToEntity(AddMovieDto addMovieDto) {
        Movie movie = Movie.builder().movieName(addMovieDto.getMovieName()).duration(addMovieDto.getDuration()).
                    releaseDate(addMovieDto.getReleaseDate()).genre(addMovieDto.getGenre()).language(addMovieDto.getLanguage()).
                build();
        return movie;
    }
}
