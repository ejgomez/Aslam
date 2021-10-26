package ejgomeznieto.tokenizer;

import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.batch.item.file.transform.RangeArrayPropertyEditor;

public class Lectoc1HeaderLineTokenizer extends FixedLengthTokenizer {

	public Lectoc1HeaderLineTokenizer() {
		final RangeArrayPropertyEditor range = new RangeArrayPropertyEditor();
		range.setAsText("1-1,2-3,4-5,6-7,8-13,14-40");
		setNames(new String[] { "espacios0", "annoProceso", "mesProceso", "diaProceso", "annoYFecha", "espacios1"});
		setColumns((Range[]) range.getValue());
	}

}
