package ejgomeznieto.tokenizer;

import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.batch.item.file.transform.RangeArrayPropertyEditor;

public class Lectoc1LineTokenizer extends FixedLengthTokenizer {

	public Lectoc1LineTokenizer() {
		final RangeArrayPropertyEditor range = new RangeArrayPropertyEditor();
		range.setAsText("1-1,2-2,3-7,8-12,13-15,16-21,22-32,33-34,35-37,38-40");
		setNames(new String[] { "tipoRegistro", "tipoCartera", "fechaProceso", "numeroEfecto",
				"numeroVencimiento", "vencimiento", "importe", "cajetin", "primera", "segunda" });
		setColumns((Range[]) range.getValue());
	}

}
