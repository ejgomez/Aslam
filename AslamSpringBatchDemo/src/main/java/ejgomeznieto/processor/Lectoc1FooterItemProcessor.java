package ejgomeznieto.processor;

import org.springframework.batch.item.ItemProcessor;

import ejgomeznieto.model.database.Lectoc1;
import ejgomeznieto.model.file.Lectoc1Footer;

public class Lectoc1FooterItemProcessor implements ItemProcessor<Lectoc1Footer, Lectoc1> {

	public Lectoc1 process(Lectoc1Footer item) throws Exception {
		final Lectoc1 lecto1 = new Lectoc1();
		return lecto1;
	}

}
