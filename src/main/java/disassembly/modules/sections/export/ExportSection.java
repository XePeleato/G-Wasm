package disassembly.modules.sections.export;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;
import disassembly.conventions.Vector;
import disassembly.modules.sections.Section;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class ExportSection extends Section {

    public static final int EXPORT_SECTION_ID = 7;

    private Vector<Export> exports;

    public ExportSection(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        super(in, EXPORT_SECTION_ID);
        exports = new Vector<>(in, Export::new);
    }

    public ExportSection(List<Export> exports) {
        super(EXPORT_SECTION_ID);
        this.exports = new Vector<>(exports);
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException, InvalidOpCodeException {
        exports.assemble(out);
    }

    public List<Export> getExports() {
        return exports.getElements();
    }

    public void setExports(List<Export> exports) {
        this.exports = new Vector<>(exports);
    }
}