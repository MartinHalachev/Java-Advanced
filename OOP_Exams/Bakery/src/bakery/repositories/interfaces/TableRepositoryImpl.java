package bakery.repositories.interfaces;

import bakery.entities.tables.interfaces.BaseTable;
import bakery.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TableRepositoryImpl implements TableRepository<Table> {
    private List<Table> tables;

    public TableRepositoryImpl() {
        this.tables = new ArrayList<>();
    }

    @Override
    public Collection<Table> getAll() {
        return this.tables;
    }

    @Override
    public void add(Table table) {
        this.tables.add(table);
    }

    @Override
    public Table getByNumber(int number) {
        for (Table table : tables) {
            if (table.getTableNumber() == number) {
                return table;
            }
        }
        return null;
    }
}
