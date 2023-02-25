package esperto.treino.Helper;

import android.database.Cursor;

public class Convercao {

    public String cursorToString(Cursor cursor) {
        StringBuilder result = new StringBuilder();
        if (cursor != null && cursor.moveToFirst()) {
            int numColumns = cursor.getColumnCount();
            do {
                for (int i = 0; i < numColumns; i++) {
                    String columnName = cursor.getColumnName(i);
                    String columnValue = cursor.getString(i);
                    result.append(columnName).append(": ").append(columnValue).append("\n");
                }
                result.append("\n");
            } while (cursor.moveToNext());
        }
        return result.toString();
    }


}
