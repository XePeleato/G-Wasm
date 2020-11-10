package disassembly.instructions.numeric;

import disassembly.InvalidOpCodeException;
import disassembly.instructions.Instr;
import disassembly.instructions.InstrType;
import disassembly.values.WDouble;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class NumericF64ConstInstr extends Instr {

    private double constValue;

    public NumericF64ConstInstr(BufferedInputStream in, InstrType instrType) throws IOException, InvalidOpCodeException {
        super(instrType);
        constValue = WDouble.read(in);
    }

    public NumericF64ConstInstr(InstrType instrType, float constValue) throws IOException {
        super(instrType);
        this.constValue = constValue;
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException {
        WDouble.write(constValue, out);
    }

    public double getConstValue() {
        return constValue;
    }

    public void setConstValue(double constValue) {
        this.constValue = constValue;
    }
}
