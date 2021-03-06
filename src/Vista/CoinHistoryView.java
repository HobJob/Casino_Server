package Vista;

import Controlador.Controller;
import Controlador.Grafics_Controllers.CoinHistory.CoinHistoryController;

/**
 * Classe que gestiona els "coin history" del servidor.
 * Consisteix en un panell buit que s'afegeix en el card layout
 * de la finestra principal, per a carregar-hi un GraphicsController
 * en el moment en el que es vulgui accedir a la vista i visualitzar
 * el contingut corresponent.
 *
 * Important no crear coin history de no ser necessari, i tancar-lo
 * al deixar de veure-ho, per a optimitzar recursos.
 *
 * @version 2.0
 */
public class CoinHistoryView extends View {

    /**Panell que automatitza el funcionament dels grafics a mostrar*/
    private GraphicsManager gp;

    /**Controlador que dirigeix els elements a mostrar per pantalla*/
    private CoinHistoryController chc;

    private Controller cg;

    /**
     * Mètode que inicia una coin history donat un nom d'usuari del que
     * obtindrà la informació necessària de la base de dades.
     * @param username Nom del usuari a generar la taula
     * @param width Amplada inicial de la pantalla
     * @param height Alçada inicial de la pantalla
     */
    public void createCoinHistory(String username, int width, int height) {
        if (chc == null) {
            chc = new CoinHistoryController(width, height, username, cg);
        } else {
            chc.initGraf(width, height, username);
        }

        gp = new GraphicsManager(this, chc);
    }

    /**
     * Mètode per a tancar la vista i retornar al rànking
     */
    public void closeView() {
        if(gp != null)  gp.exit();
        updateUI();
    }

    /**
     * Mètode per a actualitzar les variables de les dimensions de la finestra
     * @param full Pantalla completa?
     */
    public void updateSize(boolean full) {
        if (gp != null) {
            chc.updateSize(getWidth(), getHeight());
            gp.updateSize(this.getWidth(), this.getHeight(), full);
            updateUI();
        }
    }

    /**
     * Mètode per a afegir el controlador a la finestra
     * @param c Controlador del joc
     */
    @Override
    public void addController(Controller c) {
        addComponentListener(c);
        cg = c;
    }
}
