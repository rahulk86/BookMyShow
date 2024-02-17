package com.example.bookmyshow;

import com.example.bookmyshow.modal.*;
import com.example.bookmyshow.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class BookMyShowApplication {

    public static void main(String[] args) {
        ApplicationContext run = SpringApplication.run(BookMyShowApplication.class, args);
//        runForTest(run);

    }
    private static void runForTest(ApplicationContext run){
        RegionRepository regionRepository = run.getBean(RegionRepository.class);
        Region region = new Region();
        region.setLatitude(13.0350457);
        region.setLongitude(77.5970752);
        region.setName("Bangalore");
        regionRepository.save(region);
        TheaterRepository theaterRepository = run.getBean(TheaterRepository.class);

        Theater theater = new Theater();
        theater.setLatitude(13.0350457);
        theater.setLongitude(77.5970752);
        theater.setAddress("rt nagar hebbal");
        theater.setName("rk movie theater");
        theater.setRegion(region);
        theaterRepository.save(theater);

        ScreenRepository screenRepository = run.getBean(ScreenRepository.class);
        Screen screen = new Screen();
        screen.setFeatures(List.of(Features.IMAX,Features.THREE_D,Features.TWO_D));
        screen.setName("screen theater");
        screen.setTheater(theater);
        screenRepository.save(screen);

        SeatRepository seatRepository = run.getBean(SeatRepository.class);
        createSeat(screen,seatRepository,1,SeatType.GOLD);
        createSeat(screen,seatRepository,2,SeatType.DIAMOND);
        createSeat(screen,seatRepository,3,SeatType.SILVER);


        MovieRepository movieRepository = run.getBean(MovieRepository.class);
        ShowRepository showRepository = run.getBean(ShowRepository.class);
        ActorRepository actorRepository = run.getBean(ActorRepository.class);
        ShowSeatRepository showSeatRepository = run.getBean(ShowSeatRepository.class);

        Movie movie = new Movie();
        movie.setName("k.g.f");
        movie.setDuration(140.0);
        movie.setRating(5.0);
        movie.setGenre("action");
        movie.setLanguage("Hindi");
        movieRepository.save(movie);

        Actors actors = new Actors();
        actors.setMovie(movie);
        actors.setName("yash");
        actorRepository.save(actors);

        actors = new Actors();
        actors.setMovie(movie);
        actors.setName("sanjaydatt");
        actorRepository.save(actors);


        Show show = new Show();
        show.setShowStatus(ShowStatus.Upcoming);
        show.setMovie(movie);
        show.setStartTime(LocalDateTime.parse("2024-02-13T10:15:30"));
        show.setEndTime(LocalDateTime.parse("2024-02-13T13:00:30"));
        show.setScreen(screen);
        showRepository.save(show);
        creteShowSeat(show.getId(),showRepository,showSeatRepository);

        movie = new Movie();
        movie.setName("hanuman");
        movie.setDuration(140.0);
        movie.setRating(5.0);
        movie.setGenre("bhakti");
        movie.setLanguage("Hindi");
        movieRepository.save(movie);

        actors = new Actors();
        actors.setMovie(movie);
        actors.setName("new hero");
        actorRepository.save(actors);

        actors = new Actors();
        actors.setMovie(movie);
        actors.setName("new second hero");
        actorRepository.save(actors);

        show = new Show();
        show.setShowStatus(ShowStatus.Upcoming);
        show.setMovie(movie);
        show.setStartTime(LocalDateTime.parse("2024-02-13T13:15:30"));
        show.setEndTime(LocalDateTime.parse("2024-02-13T15:00:30"));
        show.setScreen(screen);
        showRepository.save(show);
        creteShowSeat(show.getId(),showRepository,showSeatRepository);

        movie = new Movie();
        movie.setName("salaar");
        movie.setDuration(140.0);
        movie.setRating(5.0);
        movie.setGenre("action");
        movie.setLanguage("Hindi");
        movieRepository.save(movie);

        show = new Show();
        show.setShowStatus(ShowStatus.Upcoming);
        show.setMovie(movie);
        show.setStartTime(LocalDateTime.parse("2024-02-13T15:15:30"));
        show.setEndTime(LocalDateTime.parse("2024-02-13T18:00:30"));
        show.setScreen(screen);
        showRepository.save(show);
        creteShowSeat(show.getId(),showRepository,showSeatRepository);

        actors = new Actors();
        actors.setMovie(movie);
        actors.setName("prabhash");
        actorRepository.save(actors);

    }

    private static void creteShowSeat(Long showId,ShowRepository showRepository,ShowSeatRepository seatRepository){
        Show show = showRepository.findById(showId).get();
        show.getScreen().getSeats().forEach(seat -> {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeat(seat);
            showSeat.setShow(show);
            showSeat.setStatus(ShowSeatStatus.AVAILABLE);
            seatRepository.save(showSeat);
        });
    }
    private static void  createSeat(Screen screen,SeatRepository seatRepository,int number,SeatType type){
        int col = 5*(number-1)+1;
        while(col<=5*number) {
            int row = 0;
            while(row<=10) {
                Seat seat = new Seat();
                seat.setSeatCol(col);
                seat.setSeatRow(row);
                seat.setSeatType(type);
                seat.setNumber(type.name()+" :: "+row+col);
                seat.setScreen(screen);
                seatRepository.save(seat);
                row++;
            }
            col++;
        }
    }

}
