package ejgomeznieto.processor;

import org.springframework.batch.item.ItemProcessor;

import ejgomeznieto.model.database.Lectoc1;
import ejgomeznieto.model.file.Lectoc1Header;

public class Lectoc1HeaderItemProcessor implements ItemProcessor<Lectoc1Header, Lectoc1> {

	public Lectoc1 process(Lectoc1Header item) throws Exception {
		final Lectoc1 lecto1 = new Lectoc1();
		return lecto1;
	}

}
