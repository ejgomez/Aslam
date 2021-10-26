package ejgomeznieto.tokenizer;

import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.batch.item.file.transform.RangeArrayPropertyEditor;

public class Lectoc1FooterLineTokenizer extends FixedLengthTokenizer {

	public Lectoc1FooterLineTokenizer() {
		final RangeArrayPropertyEditor range = new RangeArrayPropertyEditor();
		range.setAsText("1-1,2-7,8-21,22-40");
		setNames(new String[] { "finalRegistro", "totalNumeroEfectos", "totalImporteEfectos", "espacios0" });
		setColumns((Range[]) range.getValue());
	}

}
