package backend;

import backend.Entities.Category;
import backend.Entities.Client;
import backend.Entities.Product;
import backend.Entities.Roles;
import backend.ressources.CategoryController;
import backend.ressources.ClientController;
import backend.ressources.ProductController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BackendApplication {

	public static void main(String[] args) throws IOException {
		ApplicationContext applicationContext =  SpringApplication.
				run(BackendApplication.class, args);
		CategoryController categoryController= applicationContext.getBean(CategoryController.class);
		ProductController productController = applicationContext.getBean(ProductController.class);
		ClientController clientController = applicationContext.getBean(ClientController.class);

		/*
		Preparing default admin
		 */
		Roles role = new Roles();
		role.setNomrole("admin");
		role.setId(null);
		Client client = new Client();
		client.setName("fst2021");
		client.setZipCode("fst2021_1068");
		client.setCountry("Tunisie");
		client.setPhoneNumber("12345698");
		client.setUsername("fst2021@gmail.com");
		client.setAddress("Tunis_Fst");
		client.setPassword("1234");
		client.getRoles().add(role);
		client.setId(null);
		clientController.saveClient(client);

        String imageDirectory = "images/products";
		Path productImagePath = Paths.get(imageDirectory);
		if (! Files.exists(productImagePath)){
			Files.createDirectories(productImagePath);
		}

		Category c1= new Category();
		c1.setId(null);
		c1.setName("Computer");
		c1.setDescription("Un ordinateur est un système de traitement de l'information programmable tel que défini par Alan Turing et qui fonctionne par la lecture séquentielle d'un ensemble d'instructions, organisées en programmes, qui lui font exécuter des opérations logiques et arithmétiques");
		c1.setPhoto("c.png");

		Category c2= new Category();
		c2.setId(null);
		c2.setName("Smartphone");
		c2.setDescription("Un smartphone est un téléphone mobile disposant en général d'un écran tactile, d'un appareil photographique numérique, des fonctions d'un assistant numérique personnel et de certaines fonctions d'un ordinateur portable");
		c2.setPhoto("s.png");

		Category c3= new Category();
		c3.setId(null);
		c3.setName("Smartwatch");
		c3.setDescription("Une smartwatch ou montre intelligente est une montre bracelet informatisée avec des fonctionnalités allant au-delà du simple affichage de l'heure et du chronométrage, présentant des caractéristiques comparables à celles d'un PDA. Il faut les considérer comme des ordinateurs de poignet");
		c3.setPhoto("sm.png");

		Category c4= new Category();
		c4.setId(null);
		c4.setName("Montre");
		c4.setDescription("Une montre est un instrument de mesure du temps qui se porte sur soi. Une montre diffère d’une horloge, d’une pendule ou de tout autre instrument de mesure du temps par le fait qu’elle peut être emportée lors de déplacements sans que son fonctionnement soit altéré");
		c4.setPhoto("m.jpg");

		Category c5= new Category();
		c5.setId(null);
		c5.setName("Imprimante");
		c5.setDescription("Une imprimante est un engin permettant d'obtenir un document sur papier à partir d'un modèle informatique du document. Par exemple, un texte écrit via un logiciel de traitement de texte sur ordinateur pourra être imprimé pour en obtenir une version papier");
		c5.setPhoto("i.png");

		Category c6= new Category();
		c6.setId(null);
		c6.setName("Lunette");
		c6.setDescription("Les lunettes sont un instrument permettant de pallier les défauts visuels ou de protéger les yeux. Une paire de lunettes est constituée d'une monture sur laquelle sont fixés des verres correcteurs ou protecteurs et reposant sur le nez et sur les oreilles par deux branches");
		c6.setPhoto("l.png");

		Category c7= new Category();
		c7.setId(null);
		c7.setName("Tablette");
		c7.setDescription("Une tablette tactile, tablette électronique, tablette numérique, ou tout simplement tablette, est un assistant personnel ou un ordinateur portable ultraplat qui se présente sous la forme d'un écran tactile sans clavier et qui offre à peu près les mêmes fonctionnalités qu'un ordinateur personnel");
		c7.setPhoto("t.png");

		Category c8= new Category();
		c8.setId(null);
		c8.setName("Boucle");
		c8.setDescription("Une boucle d’oreille est un bijou ornant le pavillon de l’oreille, traditionnellement au niveau du lobe. Porté par paire, ce bijou est généralement considéré comme un accessoire féminin. La plupart des boucles d'oreille imposent d'avoir les oreilles percées. Il existe cependant des alternatives");
		c8.setPhoto("b.png");

		Category c9= new Category();
		c9.setId(null);
		c9.setName("Parfum");
		c9.setDescription("Un parfum est une odeur ou plus souvent une composition odorante plus ou moins persistante naturellement émise par une plante, un animal, un champignon ou un environnement. Dans la nature, les parfums sont souvent des messages chimiques et biochimiques, et notamment des phéromones ou phytohormones");
		c9.setPhoto("p.png");

		Category c10= new Category();
		c10.setId(null);
		c10.setName("Crème");
		c10.setDescription("La crème est un produit laitier, un concentré issu du lait riche en matière grasse. Elle est obtenue soit mécaniquement par centrifugation, soit naturellement par décantation du lait cru. La matière grasse de la crème est le beurre");
		c10.setPhoto("cr.png");

		Category c11= new Category();
		c11.setId(null);
		c11.setName("Savon");
		c11.setDescription("Le savon est un produit liquide ou solide composé de molécules amphiphiles obtenues par réaction chimique entre un corps gras et une base forte, spécifiquement l'hydroxyde de sodium pour le savon ou l'hydroxyde de potassium pour le savon noir, c'est donc le sel d'un corps gras et du potassium ou du sodium");
		c11.setPhoto("sa.png");
		c1 = categoryController.saveCategory(c1);
		c2 = categoryController.saveCategory(c2);
		c3 = categoryController.saveCategory(c3);
		c4 = categoryController.saveCategory(c4);
		c5 = categoryController.saveCategory(c5);
		c6 = categoryController.saveCategory(c6);
		c7 = categoryController.saveCategory(c7);
		c8 = categoryController.saveCategory(c8);
		c9 = categoryController.saveCategory(c9);
		c10 = categoryController.saveCategory(c10);
		c11 = categoryController.saveCategory(c11);

		Product p1 = new Product();
		p1.setIdproduct(null);
		p1.setDescription("Avene COUVRANCE Mascara Haute Tolérance NOIR, 7ml");
		p1.setCategory(c1);
		p1.setName("Laptop");
		p1.setAvaible(true);
		p1.setPhotoname("laptop1.png");
		p1.setPrice(41.245f);
		p1.setPromotion(false);
		p1.setSelected(false);

		Product p2 = new Product();
		p2.setIdproduct(null);
		p2.setDescription("Pour un teint impeccable !\n" +
				"\n" +
				"Action anti-âge\n" +
				"SPF 10\n" +
				"Clarifie & prévient les impuretés\n" +
				"Unifie le teint\n" +
				"Hydrate");
		p2.setCategory(c1);
		p2.setName("Printer");
		p2.setAvaible(true);
		p2.setPhotoname("printer2.png");
		p2.setPrice(53.245f);
		p2.setPromotion(false);
		p2.setSelected(true);


		Product p3 = new Product();
		p3.setIdproduct(null);
		p3.setDescription("A base d'extraits végétaux, le Soin Lavant Color & Soin® protège les pigments capillaires. " +
				"La brillance et la luminosité de votre couleur claire sont préservées jour après jour.");
		p3.setCategory(c2);
		p3.setName("Smartwatch");
		p3.setAvaible(true);
		p3.setPhotoname("smartwatch1.png");
		p3.setPrice(27.245f);
		p3.setPromotion(true);
		p3.setSelected(false);

		Product p4 = new Product();
		p4.setIdproduct(null);
		p4.setDescription("Téléphone de classe");
		p4.setCategory(c2);
		p4.setName("Smartphone");
		p4.setAvaible(true);
		p4.setPhotoname("smartphone2.png");
		p4.setPrice(600.245f);
		p4.setPromotion(true);
		p4.setSelected(true);

		productController.saveProduct(p1);
		productController.saveProduct(p2);
		productController.saveProduct(p3);
		productController.saveProduct(p4);
	}

	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
