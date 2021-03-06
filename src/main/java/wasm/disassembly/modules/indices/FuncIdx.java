package wasm.disassembly.modules.indices;

import com.sun.org.apache.xpath.internal.functions.FuncId;
import wasm.disassembly.InvalidOpCodeException;
import wasm.disassembly.WASMOpCode;
import wasm.disassembly.modules.Module;
import wasm.disassembly.values.WUnsignedInt;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class FuncIdx extends WASMOpCode {

    private long x;

    public FuncIdx(BufferedInputStream in, Module module) throws IOException, InvalidOpCodeException {
        x = WUnsignedInt.read(in, 32);
        module.getAllFuncIdxs().add(this);
    }

    public FuncIdx(long x, Module module) {
        this.x = x;
        module.getAllFuncIdxs().add(this);
    }

    @Override
    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
        WUnsignedInt.write(x, out, 32);
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }
}
