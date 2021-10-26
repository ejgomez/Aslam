package ejgomeznieto.processor;

import org.springframework.batch.item.ItemProcessor;

import ejgomeznieto.model.database.Lectoc1;
import ejgomeznieto.model.file.Lectoc1Line;

public class Lectoc1LineItemProcessor implements ItemProcessor<Lectoc1Line, Lectoc1> {

	public Lectoc1 process(Lectoc1Line item) throws Exception {
		final Lectoc1 lecto1 = new Lectoc1();
		return lecto1;
	}

}
