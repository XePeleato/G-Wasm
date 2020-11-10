package disassembly.modules.indices;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;
import disassembly.values.WUnsignedInt;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class GlobalIdx extends WASMOpCode {

    private long x;

    public GlobalIdx(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        x = WUnsignedInt.read(in, 32);
    }

    public GlobalIdx(long x) {
        this.x = x;
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
