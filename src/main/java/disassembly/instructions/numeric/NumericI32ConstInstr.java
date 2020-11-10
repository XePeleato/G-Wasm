package disassembly.instructions.numeric;

import disassembly.InvalidOpCodeException;
import disassembly.instructions.Instr;
import disassembly.instructions.InstrType;
import disassembly.values.WSignedInt;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class NumericI32ConstInstr extends Instr {

    private int constValue;

    public NumericI32ConstInstr(BufferedInputStream in, InstrType instrType) throws IOException, InvalidOpCodeException {
        super(instrType);
        constValue = WSignedInt.read(in, 32);
    }

    public NumericI32ConstInstr(InstrType instrType, int constValue) throws IOException {
        super(instrType);
        this.constValue = constValue;
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException, InvalidOpCodeException {
        WSignedInt.write(constValue, out, 32);
    }

    public int getConstValue() {
        return constValue;
    }

    public void setConstValue(int constValue) {
        this.constValue = constValue;
    }
}
