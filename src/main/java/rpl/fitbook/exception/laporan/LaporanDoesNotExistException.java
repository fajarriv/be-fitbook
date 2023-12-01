package rpl.fitbook.exception.laporan;

public class LaporanDoesNotExistException extends RuntimeException{
    public LaporanDoesNotExistException(Integer laporanId) {
        super("Laporan with id " + laporanId + " does not exist");
    }

    public LaporanDoesNotExistException(String pelapor) {
        super("Laporan with pelapor " + pelapor + " does not exist");
    }
}
