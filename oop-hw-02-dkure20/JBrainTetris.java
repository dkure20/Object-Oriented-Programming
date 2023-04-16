import javax.swing.*;

public class JBrainTetris extends JTetris{
    private JCheckBox brainMode;
    private JCheckBox animateFall;
    private DefaultBrain defBrain;
    private int brainCount;
    private Brain.Move brainMove;
    private Brain.Move move;
    private JSlider adversary;
    private JPanel little;
    private JLabel okLabel;
    /**
     *
     * Creates a new JTetris where each tetris square
     * is drawn with the given number of pixels.
     *
     * @param pixels
     */
    JBrainTetris(int pixels) {
        super(pixels);
        defBrain = new DefaultBrain();
        brainCount = 0;
        brainMove = new Brain.Move();
    }
    public static void main(String[] args) {
        // Set GUI Look And Feel Boilerplate.
        // Do this incantation at the start of main() to tell Swing
        // to use the GUI LookAndFeel of the native platform. It's ok
        // to ignore the exception.
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }

        JBrainTetris tetris = new JBrainTetris(16);
        JFrame frame = JTetris.createFrame(tetris);
        frame.setVisible(true);
    }
    @Override
    public JComponent createControlPanel(){
        JPanel panel = (JPanel)super.createControlPanel();
        panel.add(new JLabel("Brain:"));
        brainMode = new JCheckBox("Brain active");
        animateFall = new JCheckBox("Animate Falling: ");
        animateFall.setSelected(true);
        panel.add(brainMode);
        panel.add(animateFall);
        little = new JPanel();
        panel.add(little);
        little.add(new JLabel("Adversary:"));
        adversary = new JSlider(0, 100, 0);
        little.add(adversary);
        okLabel = new JLabel("OK");
        panel.add(okLabel);
        panel.add(Box.createVerticalStrut(15));
        return panel;

    }
    @Override
    public void tick(int verb) {
        if (verb == DOWN && brainMode.isSelected()) {
            if (brainCount != count) {
                brainCount = count;
                board.undo();
                brainMove = defBrain.bestMove(board,currentPiece, HEIGHT, brainMove);
            }
            if (brainMove != null) {
                if (!brainMove.piece.equals(currentPiece))
                    super.tick(ROTATE);
                if (brainMove.x > currentX){
                    super.tick(RIGHT);
                }
                else if (brainMove.x < currentX){
                    super.tick(LEFT);
                }
                else if (!animateFall.isSelected()){
                    if(brainMove.x == currentX && currentY > brainMove.y){
                        super.tick(DROP);
                    }
                }
            }
        }
        super.tick(verb);
    }
    @Override
    public Piece pickNextPiece() {
        int sliderNum = adversary.getValue();
        int randomNumber = random.nextInt(100);
        if(randomNumber>= sliderNum){
            okLabel.setText("ok");
            return super.pickNextPiece();
        }else{
            okLabel.setText("*ok*");
            Piece worst = super.pickNextPiece();
            double worstScore =0;
            for(int i=0; i<pieces.length; i++){
                move = defBrain.bestMove(board,pieces[i],HEIGHT,move);
                if(move!=null && move.score > worstScore){
                    worst = pieces[i];
                    worstScore = move.score;
                }
            }
            return worst;
        }
    }

}
