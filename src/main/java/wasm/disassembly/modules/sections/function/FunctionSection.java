package wasm.disassembly.modules.sections.function;

import wasm.disassembly.InvalidOpCodeException;
import wasm.disassembly.conventions.Vector;
import wasm.disassembly.modules.Module;
import wasm.disassembly.modules.indices.TypeIdx;
import wasm.disassembly.modules.sections.Section;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class FunctionSection extends Section {

    public static final int FUNCTION_SECTION_ID = 3;

    private Vector<TypeIdx> typeIdxVector;


    public FunctionSection(BufferedInputStream in, Module module) throws IOException, InvalidOpCodeException {
        super(in, module, FUNCTION_SECTION_ID);
        typeIdxVector = new Vector<>(in, TypeIdx::new, module);
    }

    public FunctionSection(Module module, List<TypeIdx> typeIdxList) {
        super(module, FUNCTION_SECTION_ID);
        this.typeIdxVector = new Vector<>(typeIdxList);
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException, InvalidOpCodeException {
        typeIdxVector.assemble(out);
    }

    public List<TypeIdx> getTypeIdxVector() {
        return typeIdxVector.getElements();
    }

    public void setTypeIdxVector(List<TypeIdx> typeIdxVector) {
        this.typeIdxVector = new Vector<>(typeIdxVector);
    }

}
