package pe.edu.upeu.calcfx.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pe.edu.upeu.calcfx.modelo.tictacTO;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;




@Component
public class TictacControl {
    @FXML
    private TableView<Partida> tablaPartidas;
    @FXML
    private TableColumn<Partida, Integer> colNumeroPartida;
    @FXML
    private TableColumn<Partida, String> colJugador1;
    @FXML
    private TableColumn<Partida, String> colJugador2;
    @FXML
    private TableColumn<Partida, String> colGanador;
    @FXML
    private TableColumn<Partida, Integer> colPuntuacion;
    @FXML
    private TableColumn<Partida, String> colEstado;


    private int numeroPartida = 1;
    private ObservableList<Partida> listaPartidas = FXCollections.observableArrayList();




    Button[][] tablero;

    @FXML
    Button btn00, btn01, btn02,btn10, btn11, btn12, btn20,btn21, btn22;

    boolean turno=true;
    @FXML
    public void initialize() {
        System.out.println("BIENVENIDOS AL TIK TAK TOE ");
        tablero=new Button[][]{
                {btn00, btn01,btn02},
                {btn10, btn11, btn12},
                {btn20,btn21, btn22}


        };
            // Configura las columnas de la tabla
            colNumeroPartida.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getNumeroPartida()).asObject());
            colJugador1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getJugador1()));
            colJugador2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getJugador2()));
            colGanador.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGanador()));
            colPuntuacion.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPuntuacion()).asObject());
            colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstado()));

            // Inicializa el ObservableList de partidas
            tablaPartidas.setItems(listaPartidas);
        }

    @FXML
    private Label contador1;
    @FXML
    private Label contador2;

    // Contadores de turnos
    private int contadorTurnosJ1 = 0;
    private int contadorTurnosJ2 = 0;
    // ObservableList para almacenar los registros de turnos (opcional para una tabla)
    private ObservableList<tictacTO.RegistroTurno> registrosTurnos = FXCollections.observableArrayList();
    @FXML
    void accionButon(ActionEvent e){
        Button b=(Button)e.getSource();
        b.setText(turno?"X":"O");
        turno=!turno;

        // Cambiar el turno después de presionar el botón
        if (turno) {
            contadorTurnosJ1++;
        } else {
            contadorTurnosJ2++;
        }String jugadorActualString = turno ? listaJugadores.get(0) : listaJugadores.get(1);


        actualizarTurno();
    }


    @FXML
    void imprimir(){
        for (int i=0;i<tablero.length; i++ ){
            for (int j=0;j<tablero[0].length; j++ ){
                System.out.print(tablero[i][j].getText()+"\t");
            }
            System.out.println("");
        }
    }

    @FXML private TextField NombJ1;
    @FXML private TextField NombJ2;

    // ArrayList para almacenar los nombres de los jugadores
    private ArrayList<String> listaJugadores = new ArrayList<>();

    // Label para mostrar el turno del jugador
    @FXML private Label jugaturno;

    // Método que se ejecuta cuando se hace clic en el botón "Iniciar"
    @FXML
    public void iniciarJuego() {
        // Capturar los nombres de los TextField
        String nombreJugador1 = NombJ1.getText();
        String nombreJugador2 = NombJ2.getText();

        // Guardar los nombres en el ArrayList
        listaJugadores.add(nombreJugador1);
        listaJugadores.add(nombreJugador2);

        // Verificar si los nombres se han guardado correctamente
        System.out.println("Jugador 1: " + listaJugadores.get(0));
        System.out.println("Jugador 2: " + listaJugadores.get(1));

        // Inicializar el turno del juego
        jugaturno.setText("TURNO: " + listaJugadores.get(0));
        // Inicializar los contadores de turnos
        contadorTurnosJ1 = 0;
        contadorTurnosJ2 = 0;
        contador1.setText(": " + contadorTurnosJ1);
        contador2.setText(": " + contadorTurnosJ2);
    }


    // Método para actualizar el turno en la etiqueta
    private void actualizarTurno() {
        if (listaJugadores.size() >= 2) {
            // Si el turno es true, es el turno del Jugador 1, si es false, es el turno del Jugador 2
            String jugadorTurno = turno ? listaJugadores.get(0) : listaJugadores.get(1);
            jugaturno.setText("TURNO: " + jugadorTurno);
        } else {
            jugaturno.setText("TURNO: -");
        }
    }

    // Método para limpiar el tablero al iniciar el juego
    private void limpiarTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                tablero[i][j].setText(""); // Borra el contenido de cada botón
            }
        }
    }
    public class Partida {
        private final SimpleIntegerProperty numeroPartida;
        private final SimpleStringProperty jugador1;
        private final SimpleStringProperty jugador2;
        private final SimpleStringProperty ganador;
        private final SimpleIntegerProperty puntuacion;
        private final SimpleStringProperty estado;

        public Partida(int numeroPartida, String jugador1, String jugador2, String ganador, int puntuacion, String estado) {
            this.numeroPartida = new SimpleIntegerProperty(numeroPartida);
            this.jugador1 = new SimpleStringProperty(jugador1);
            this.jugador2 = new SimpleStringProperty(jugador2);
            this.ganador = new SimpleStringProperty(ganador);
            this.puntuacion = new SimpleIntegerProperty(puntuacion);
            this.estado = new SimpleStringProperty(estado);
        }

        public int getNumeroPartida() {
            return numeroPartida.get();
        }

        public String getJugador1() {
            return jugador1.get();
        }

        public String getJugador2() {
            return jugador2.get();
        }

        public String getGanador() {
            return ganador.get();
        }

        public int getPuntuacion() {
            return puntuacion.get();
        }

        public String getEstado() {
            return estado.get();
        }
    }

    @FXML
    public void anularJuego() {
        System.out.println("Partida anulada");

        // Limpiar el tablero de juego
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                tablero[i][j].setText("");  // Limpiar cada botón
                tablero[i][j].setDisable(false);  // Habilitar botones
            }
        }

        // Limpiar los nombres de los jugadores
        NombJ1.clear();
        NombJ2.clear();

        // Reiniciar los contadores
        contadorTurnosJ1 = 0;
        contadorTurnosJ2 = 0;
        contador1.setText("Turnos J1: " + contadorTurnosJ1);
        contador2.setText("Turnos J2: " + contadorTurnosJ2);

        // Reiniciar el turno del juego
        jugaturno.setText("TURNO: -");

        // Si quieres también reiniciar la lista de partidas y la tabla, hazlo aquí
        listaPartidas.clear();
        tablaPartidas.setItems(listaPartidas);

        // Agregar una partida a la lista después de un juego
        String ganador = "N/A"; // Ajusta esto según el resultado real
        String estado = "Anulada";
        int puntuacion = 0; // Ajusta esto según el resultado real

        // Crea una nueva partida y añádela a la lista
        listaPartidas.add(new Partida(numeroPartida++, listaJugadores.get(0), listaJugadores.get(1), ganador, puntuacion, estado));
        tablaPartidas.setItems(listaPartidas);

        // Reiniciar la partida
        numeroPartida = 1;
    }
    @FXML
    void finalizarPartida() {
        // Aquí deberías determinar quién ganó, el puntaje y el estado de la partida.
        String ganador = determinarGanador(); // Implementa este método
        int puntuacion = calcularPuntuacion(); // Implementa este método
        String estado = "Finalizada"; // O el estado que corresponda

        // Crea una nueva partida con los datos obtenidos
        Partida nuevaPartida = new Partida(numeroPartida++, listaJugadores.get(0), listaJugadores.get(1), ganador, puntuacion, estado);

        // Añade la nueva partida a la lista
        listaPartidas.add(nuevaPartida);

        // Actualiza la tabla
        tablaPartidas.setItems(listaPartidas);
    }
    private String determinarGanador() {
        // Lógica para determinar el ganador
        // Ejemplo: Si el jugador 1 gana
        return turno ? listaJugadores.get(0) : listaJugadores.get(1);
    }

    private int calcularPuntuacion() {
        // Lógica para calcular la puntuación
        // Ejemplo: La puntuación podría ser el número de turnos jugados
        return turno ? contadorTurnosJ1 : contadorTurnosJ2;
    }

}

