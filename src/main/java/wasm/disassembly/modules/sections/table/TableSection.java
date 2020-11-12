package wasm.disassembly.modules.sections.table;

import wasm.disassembly.InvalidOpCodeException;
import wasm.disassembly.conventions.Vector;
import wasm.disassembly.modules.sections.Section;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class TableSection extends Section {

    public static final int TABLE_SECTION_ID = 4;

    private Vector<Table> tables;


    public TableSection(BufferedInputStream in) throws IOException, InvalidOpCodeException {
        super(in, TABLE_SECTION_ID);
        tables = new Vector<>(in, Table::new);
    }

    public TableSection(List<Table> tables) {
        super(TABLE_SECTION_ID);
        this.tables = new Vector<>(tables);
    }

    @Override
    protected void assemble2(OutputStream out) throws IOException, InvalidOpCodeException {
        tables.assemble(out);
    }

    public List<Table> getTables() {
        return tables.getElements();
    }

    public void setTables(List<Table> tables) {
        this.tables = new Vector<>(tables);
    }
}