package wasm.disassembly.modules.sections.element;

import wasm.disassembly.InvalidOpCodeException;
import wasm.disassembly.conventions.Vector;
import wasm.disassembly.modules.Module;
import wasm.disassembly.modules.sections.Section;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ElementSection extends Section {

    public static final int ELEMENT_SECTION_ID = 9;

    private Vector<Elem> elementSegments;

    public ElementSection(BufferedInputStream in, Module module) throws IOException, InvalidOpCodeException {
        super(in, module, ELEMENT_SECTION_ID);
        elementSegments = new Vector<>(in, Elem::new, module);
    }

    public ElementSection(Module module, List<Elem> elementSegments) {
        super(module, ELEMENT_SECTION_ID);
        this.elementSegments = new Vector<>(elementSegments);
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException, InvalidOpCodeException {
        elementSegments.assemble(out);
    }

    public List<Elem> getElementSegments() {
        return elementSegments.getElements();
    }

    public void setElementSegments(List<Elem> elementSegments) {
        this.elementSegments = new Vector<>(elementSegments);
    }
}
