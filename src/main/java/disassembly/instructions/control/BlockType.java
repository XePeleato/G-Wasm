package disassembly.instructions.control;

import disassembly.InvalidOpCodeException;
import disassembly.WASMOpCode;
import disassembly.types.ValType;
import disassembly.values.old.OldWSignedInt;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BlockType extends WASMOpCode {

    private Object value;

    public BlockType(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        in.mark(1);
        int first = in.read();

        if (first == 0x40) {
            value = null;
        }
        else if (ValType.from_val(first) != null) {
            value = ValType.from_val(first);
        }
        else {
            in.reset();
            value = new OldWSignedInt(in, 33);
        }
    }

    public BlockType(Object value) {
        this.value = value;
    }

    @Override
    public void assemble(OutputStream out) throws IOException, InvalidOpCodeException {
        if (value == null) {
            out.write(0x40);
        }
        else if (value instanceof ValType) {
            out.write(((ValType)value).val);
        }
        else if (value instanceof OldWSignedInt) {
            ((OldWSignedInt)value).assemble(out);
        }
        else {
            throw new InvalidOpCodeException("Invalid block type");
        }
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return value == null;
    }

    public boolean isValueType() {
        return value != null && value instanceof ValType;
    }

    public boolean isSignedInteger() {
        return value != null && value instanceof OldWSignedInt;
    }
}