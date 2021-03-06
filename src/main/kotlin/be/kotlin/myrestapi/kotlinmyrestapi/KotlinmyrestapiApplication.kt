package be.kotlin.myrestapi.kotlinmyrestapi

import be.kotlin.myrestapi.kotlinmyrestapi.entity.BeerEntity
import be.kotlin.myrestapi.kotlinmyrestapi.entity.BeerTypeEntity
import be.kotlin.myrestapi.kotlinmyrestapi.entity.BreweryEntity
import be.kotlin.myrestapi.kotlinmyrestapi.entity.CountryEntity
import be.kotlin.myrestapi.kotlinmyrestapi.repository.BeerRepository
import be.kotlin.myrestapi.kotlinmyrestapi.repository.BeerTypeRepository
import be.kotlin.myrestapi.kotlinmyrestapi.repository.BreweryRepository
import be.kotlin.myrestapi.kotlinmyrestapi.repository.CountryRepository
import org.h2.server.web.WebServlet
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@SpringBootApplication
class KotlinmyrestapiApplication{

    private val log = LoggerFactory.getLogger(KotlinmyrestapiApplication::class.java)


//    @Bean
//    fun h2servletRegistration(): ServletRegistrationBean {
//        val registration = ServletRegistrationBean(WebServlet())
//        registration.addUrlMappings("/console/*")
//        return registration
//    }


    @Bean
    fun init(repoBeer: BeerRepository, repoBeerType: BeerTypeRepository, repoCountry: CountryRepository, repoBrewery: BreweryRepository) = CommandLineRunner{

        //save Country

        val c =repoCountry.save(CountryEntity(1, "Belgium"))

        //save BeerTypes
        val typeBlond = repoBeerType.save(BeerTypeEntity(1, "Blonde Ale", "Blonde Ale" ))

        repoBeerType.save(BeerTypeEntity(2, "Flanders Red Ale", "Flanders Red Ale" ))
        repoBeerType.save(BeerTypeEntity(3, "Geuze", "Geuze" ))
        repoBeerType.save(BeerTypeEntity(4, "Lambic", "Lambic" ))
        val typeBrown = repoBeerType.save(BeerTypeEntity(5, "Oud Bruin", "Oud Bruin" ))


        //save Brewery
        val brewery = repoBrewery.save(BreweryEntity(1, "Abdij Westvleteren", "Donkerstraat", "12", "8640", "Vleteren",  country = c))

        //save some beers
        repoBeer.save(BeerEntity(1,"Westvleteren Blond",  5.8, "Blond", typeBlond, brewery))
        repoBeer.save(BeerEntity(2,"Westvleteren Acht",  8.0, "Brown", typeBrown, brewery))
        repoBeer.save(BeerEntity(3,"Westvleteren Twaalf", 10.8, "Brown", typeBrown, brewery))

    }
}


fun main(args: Array<String>) {
    SpringApplication.run(KotlinmyrestapiApplication::class.java, *args)
}
