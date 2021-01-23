//package com.sda.filmbook;
//
//import com.sda.filmbook.model.Copy;
//import com.sda.filmbook.model.Genre;
//import com.sda.filmbook.model.Movie;
//import com.sda.filmbook.repository.CopyRepository;
//import com.sda.filmbook.repository.MovieRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.util.LinkedList;
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//
//public class InitDataBase implements CommandLineRunner {
//    private final MovieRepository movieRepository;
//    private final CopyRepository copyRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        initAction();
//    }
//
//    public void initAction() {
//        List<Movie> movies = new LinkedList<>();
//        var movie1 = Movie.builder()
//                .title("Split")
//                .genre(Genre.THRILLER)
//                .yearOfProduction(LocalDate.of(2016, 1, 1))
//                .description("Mężczyzna o mnogiej osobowości porywa trzy nastolatki. Okazuje się, że jedna z jaźni zaczyna dominować nad innymi.").build();
//        var movie2 = Movie.builder()
//                .title("Knives Out")
//                .genre(Genre.CRIME)
//                .yearOfProduction(LocalDate.of(2019, 1, 1))
//                .description("Detektyw Blanc bada sprawę śmierci bogatego pisarza, głowy ekscentrycznej rodziny.").build();
//        var movie3 = Movie.builder()
//                .title("Joker")
//                .genre(Genre.DRAMA)
//                .yearOfProduction(LocalDate.of(2019, 1, 1))
//                .description("Strudzony życiem komik popada w obłęd i staje się psychopatycznym mordercą.").build();
//        var movie4 = Movie.builder()
//                .title("La La Land")
//                .genre(Genre.MUSICAL)
//                .yearOfProduction(LocalDate.of(2016, 8, 31))
//                .description("Los Angeles. Pianista jazzowy zakochuje się w początkującej aktorce.").build();
//        var movie5 = Movie.builder()
//                .title("Green Book")
//                .genre(Genre.COMEDY)
//                .yearOfProduction(LocalDate.of(2018, 9, 11))
//                .description("Drobny cwaniaczek z Bronksu zostaje szoferem ekstrawaganckiego muzyka z wyższych sfer i razem wyruszają na wielotygodniowe tournée.").build();
//        var movie6 = Movie.builder()
//                .title("Bohemian Rhapsody")
//                .genre(Genre.BIOGRAPHICAL)
//                .yearOfProduction(LocalDate.of(2018, 10, 23))
//                .description("Dzięki oryginalnemu brzmieniu Queen staje się jednym z najpopularniejszych zespołów w historii muzyki.").build();
//        var movie7 = Movie.builder()
//                .title("Fantastic Beasts and Where to Find Them")
//                .genre(Genre.FANTASY)
//                .yearOfProduction(LocalDate.of(2016, 11, 8))
//                .description("Nowy Jork jest terroryzowany przez tajemnicze bestie, tymczasem do miasta przyjeżdża pisarz Newt Scamander.").build();
//        var movie8 = Movie.builder()
//                .title("Doctor Strange")
//                .genre(Genre.FANTASY)
//                .yearOfProduction(LocalDate.of(2016, 10, 13))
//                .description("Potężny czarodziej doktor Strange walczy z siłami ciemności, aby ocalić świat.").build();
//        var movie9 = Movie.builder()
//                .title("Parasite")
//                .genre(Genre.DRAMA)
//                .yearOfProduction(LocalDate.of(2019, 5, 21))
//                .description("PKi-woo dostaje pracę jako korepetytor córki zamożnego małżeństwa.").build();
//        var movie10 = Movie.builder()
//                .title("Avengers: Infinity War")
//                .genre(Genre.ACTION)
//                .yearOfProduction(LocalDate.of(2018, 4, 23))
//                .description("Potężny Thanos zbiera Kamienie Nieskończoności w celu narzucenia swojej woli wszystkim istnieniom we wszechświecie.").build();
//        var movie11 = Movie.builder()
//                .title("Baby Driver")
//                .genre(Genre.ACTION)
//                .yearOfProduction(LocalDate.of(2017, 3, 11))
//                .description("Młody chłopak, zmuszany do pracy dla bossa mafii, zostaje wrobiony w napad skazany na niepowodzenie.").build();
//        var movie12 = Movie.builder()
//                .title("Nocturnal Animals")
//                .genre(Genre.THRILLER)
//                .yearOfProduction(LocalDate.of(2016, 9, 2))
//                .description("Właścicielka galerii jest porażona brutalnym thrillerem, w którym odnajduje nawiązania do swojej przeszłości.").build();
//        var movie13 = Movie.builder()
//                .title("Get Out")
//                .genre(Genre.HORROR)
//                .yearOfProduction(LocalDate.of(2017, 1, 23))
//                .description("Czarnoskóry mężczyzna udaje się ze swoją białą partnerką do posiadłości jej rodziców.").build();
//        var movie14 = Movie.builder()
//                .title("Beauty and the Beast")
//                .genre(Genre.MUSICAL)
//                .yearOfProduction(LocalDate.of(2017, 2, 23))
//                .description("Bella gotowa jest zamieszkać w zamku potwora, by ratować ojca. Z czasem między dziewczyną a bestią zaczyna rodzić się uczucie.").build();
//        var movie15 = Movie.builder()
//                .title("It")
//                .genre(Genre.HORROR)
//                .yearOfProduction(LocalDate.of(2017, 9, 5))
//                .description("Dzieci z małego miasteczka terroryzuje tajemnicza istota przybierająca postać klauna.").build();
//        var movie16 = Movie.builder()
//                .title("Coco")
//                .genre(Genre.ANIMATION)
//                .yearOfProduction(LocalDate.of(2017, 10, 20))
//                .description("Dwunastoletni meksykański chłopiec imieniem Miguel usiłuje zgłębić tajemnice rodzinnej legendy.").build();
//        var movie17 = Movie.builder()
//                .title("Tenet")
//                .genre(Genre.SCIENCE_FICTION)
//                .yearOfProduction(LocalDate.of(2020, 8, 26))
//                .description("Uzbrojony tylko w jedno słowo — Tenet — bohater przenika w mroczny świat międzynarodowych szpiegów.").build();
//        var movie18 = Movie.builder()
//                .title("Black Panther")
//                .genre(Genre.ACTION)
//                .yearOfProduction(LocalDate.of(2018, 1, 29))
//                .description("Książę T'Challa przywdziewa kostium Czarnej Pantery po tym, jak jego ojciec zostaje podstępnie zamordowany.").build();
//        var movie19 = Movie.builder()
//                .title("Dirty Grandpa")
//                .genre(Genre.COMEDY)
//                .yearOfProduction(LocalDate.of(2016, 1, 20))
//                .description("Mający niebawem się ożenić Jason zabiera swojego dziadka na przejażdżkę po Florydzie.").build();
//        var movie20 = Movie.builder()
//                .title("Jojo Rabbit")
//                .genre(Genre.DRAMA)
//                .yearOfProduction(LocalDate.of(2019, 9, 8))
//                .description("Młody Niemiec Jojo pomaga Żydówce. Jednocześnie próbuje pozostać nazistą.").build();
//        var movie21 = Movie.builder()
//                .title("The Conjuring 2")
//                .genre(Genre.HORROR)
//                .yearOfProduction(LocalDate.of(2016, 6, 7))
//                .description("Lorraine i Ed Warren udają się do Londynu, aby pomóc samotnej matce, której dom jest nawiedzany przez duchy.").build();
//        var movie22 = Movie.builder()
//                .title("10 Cloverfield Lane")
//                .genre(Genre.SCIENCE_FICTION)
//                .yearOfProduction(LocalDate.of(2016, 3, 8))
//                .description("Po wypadku samochodowym kobieta budzi się w piwnicy mężczyzny, który twierdzi, że uratował ją przed atakiem chemicznym.").build();
//        var movie23 = Movie.builder()
//                .title("Going in Style")
//                .genre(Genre.CRIME)
//                .yearOfProduction(LocalDate.of(2017, 5, 5))
//                .description("Trzej zdesperowani emeryci postanawiają napaść na bank, który pozbawił ich oszczędności.").build();
//        var movie24 = Movie.builder()
//                .title("Zootopia")
//                .genre(Genre.ANIMATION)
//                .yearOfProduction(LocalDate.of(2016, 2, 10))
//                .description("Żyjący z przekrętów lis oraz królik zatrudniony w policji, łączą siły, by rozwiązać kryminalną zagadkę.").build();
//        var movie25 = Movie.builder()
//                .title("Kong: Skull Island")
//                .genre(Genre.FANTASY)
//                .yearOfProduction(LocalDate.of(2017, 2, 28))
//                .description("Grupa odkrywców trafia na wyspę zamieszkałą przez mitycznego Konga.").build();
//        var movie26 = Movie.builder()
//                .title("Le Mans '66")
//                .genre(Genre.BIOGRAPHICAL)
//                .yearOfProduction(LocalDate.of(2019, 8, 30))
//                .description("Projektant Carroll Shelby i kierowca Ken Miles podejmują wyzwanie pokonania samochodów ekipy Ferrari w wyścigu Le Mans.").build();
//        var movie27 = Movie.builder()
//                .title("Don't Breathe")
//                .genre(Genre.THRILLER)
//                .yearOfProduction(LocalDate.of(2016, 3, 12))
//                .description("Kilkoro nastolatków próbuje okraść niewidomego mężczyznę.").build();
//        var movie28 = Movie.builder()
//                .title("Tomb Raider")
//                .genre(Genre.ADVENTURE)
//                .yearOfProduction(LocalDate.of(2018, 3, 2))
//                .description("Lara Croft wyrusza w podróż, by odnaleźć zaginionego ojca na niezbadanej wyspie u wybrzeży Japonii.").build();
//        var movie29 = Movie.builder()
//                .title("Jumanji: Welcome to the Jungle")
//                .genre(Genre.ADVENTURE)
//                .yearOfProduction(LocalDate.of(2017, 12, 5))
//                .description("Czworo przyjaciół odkrywa starą grę, która przenosi ich w świat dżungli.").build();
//        var movie30 = Movie.builder()
//                .title("Aladdin")
//                .genre(Genre.MUSICAL)
//                .yearOfProduction(LocalDate.of(2019, 5, 8))
//                .description("Chłopak o czystym sercu dostaje propozycję od potężnego wezyra, by znalazł dla niego tajemniczą lampę.").build();
//        var movie31 = Movie.builder()
//                .title("Us")
//                .genre(Genre.HORROR)
//                .yearOfProduction(LocalDate.of(2019, 3, 8))
//                .description("Wakacje pewnej rodziny zakłócają nieproszeni goście, którzy nie mają dobrych intencji.").build();
//        var movie32 = Movie.builder()
//                .title("Lights Out")
//                .genre(Genre.HORROR)
//                .yearOfProduction(LocalDate.of(2016, 3, 8))
//                .description("Kobieta jest nawiedzana przez stwora, który pojawia się po zgaszeniu światła.").build();
//        var movie33 = Movie.builder()
//                .title("A Simple Favor")
//                .genre(Genre.COMEDY)
//                .yearOfProduction(LocalDate.of(2016, 3, 8))
//                .description("Kobieta jest nawiedzana przez stwora, który pojawia się po zgaszeniu światła.").build();
//        var movie34 = Movie.builder()
//                .title("Jungle")
//                .genre(Genre.THRILLER)
//                .yearOfProduction(LocalDate.of(2017, 8, 3))
//                .description("Trzech przyjaciół zwiedzających Amerykę Południową postanawia przeżyć przygodę.").build();
//        var movie35 = Movie.builder()
//                .title("Hunt for the Wilderpeople")
//                .genre(Genre.COMEDY)
//                .yearOfProduction(LocalDate.of(2016, 1, 22))
//                .description("Ricky trafia do rodziny zastępczej. Po śmierci przybranej ciotki, chłopak ucieka.").build();
//
//
//        movieRepository.save(movie1);
//        movieRepository.save(movie2);
//        movieRepository.save(movie3);
//        movieRepository.save(movie4);
//        movieRepository.save(movie5);
//        movieRepository.save(movie6);
//        movieRepository.save(movie7);
//        movieRepository.save(movie8);
//        movieRepository.save(movie9);
//        movieRepository.save(movie10);
//        movieRepository.save(movie11);
//        movieRepository.save(movie12);
//        movieRepository.save(movie13);
//        movieRepository.save(movie14);
//        movieRepository.save(movie15);
//        movieRepository.save(movie16);
//        movieRepository.save(movie17);
//        movieRepository.save(movie18);
//        movieRepository.save(movie19);
//        movieRepository.save(movie20);
//        movieRepository.save(movie21);
//        movieRepository.save(movie22);
//        movieRepository.save(movie23);
//        movieRepository.save(movie24);
//        movieRepository.save(movie25);
//        movieRepository.save(movie26);
//        movieRepository.save(movie27);
//        movieRepository.save(movie28);
//        movieRepository.save(movie29);
//        movieRepository.save(movie30);
//        movieRepository.save(movie31);
//        movieRepository.save(movie32);
//        movieRepository.save(movie33);
//        movieRepository.save(movie34);
//        movieRepository.save(movie35);
//
//
//        movie1.setCopies(new LinkedList<>());
//        movie2.setCopies(new LinkedList<>());
//        movie3.setCopies(new LinkedList<>());
//        movie4.setCopies(new LinkedList<>());
//        movie5.setCopies(new LinkedList<>());
//        movie6.setCopies(new LinkedList<>());
//        movie7.setCopies(new LinkedList<>());
//        movie8.setCopies(new LinkedList<>());
//        movie9.setCopies(new LinkedList<>());
//        movie10.setCopies(new LinkedList<>());
//        movie11.setCopies(new LinkedList<>());
//        movie12.setCopies(new LinkedList<>());
//        movie13.setCopies(new LinkedList<>());
//        movie14.setCopies(new LinkedList<>());
//        movie15.setCopies(new LinkedList<>());
//        movie16.setCopies(new LinkedList<>());
//        movie17.setCopies(new LinkedList<>());
//        movie18.setCopies(new LinkedList<>());
//        movie19.setCopies(new LinkedList<>());
//        movie20.setCopies(new LinkedList<>());
//        movie21.setCopies(new LinkedList<>());
//        movie22.setCopies(new LinkedList<>());
//        movie23.setCopies(new LinkedList<>());
//        movie24.setCopies(new LinkedList<>());
//        movie25.setCopies(new LinkedList<>());
//        movie26.setCopies(new LinkedList<>());
//        movie27.setCopies(new LinkedList<>());
//        movie28.setCopies(new LinkedList<>());
//        movie29.setCopies(new LinkedList<>());
//        movie30.setCopies(new LinkedList<>());
//        movie31.setCopies(new LinkedList<>());
//        movie32.setCopies(new LinkedList<>());
//        movie33.setCopies(new LinkedList<>());
//        movie34.setCopies(new LinkedList<>());
//        movie35.setCopies(new LinkedList<>());
//
//        for (Movie movieElement :
//                movies) {
//            Copy copy = Copy.builder().movie(movieElement).build();
//            copyRepository.save(copy);
//            movieElement.getCopies().add(copy);
//            movieRepository.save(movieElement);
//        }
//    }
//
//}