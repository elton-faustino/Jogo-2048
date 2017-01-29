import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

public class _2048 extends JFrame implements KeyListener {
	
	private JPanel contentPane;
	public JButton botoes [][] = new JButton[4][4];	
	public ArrayList<Sorteio> lista = new ArrayList<Sorteio>();
	public int m[][] = new int[][]{
			{0,0,0,0},
			{0,0,7,0},
			{0,7,0,0},
			{0,0,0,0}
	};	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				        System.out.println(info.getName());
						if ("Nimbus".equals(info.getName())) {
				            UIManager.setLookAndFeel(info.getClassName());
				            break;
				        }
				    }
					_2048 frame = new _2048();
					frame.setTitle("Elton Bonitão");
					frame.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public _2048() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 740, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnCima = new JButton("Cima");
		btnCima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eC();				
			}
		});
		btnCima.setBounds(459, 318, 89, 23);
		contentPane.add(btnCima);
		
		JButton btnBaixo = new JButton("Baixo");
		btnBaixo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eB();				
			}
		});
		btnBaixo.setBounds(459, 364, 89, 23);
		contentPane.add(btnBaixo);
		
		JButton btnEsquerda = new JButton("Esquerda");
		btnEsquerda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eE();
			}
		});
		btnEsquerda.setBounds(411, 341, 89, 23);
		contentPane.add(btnEsquerda);
		
		JButton btnDireita = new JButton("Direita");
		btnDireita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eD();
			}
		});
		btnDireita.setBounds(500, 341, 89, 23);
		contentPane.add(btnDireita);
	
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent arg0) {
				reset();
			}			
		});
		btnReset.setBounds(633, 341, 89, 23);
		contentPane.add(btnReset);
				
		addKeyListener(this);
		
		refresh(true);
		
	}
			
	public void sorteio(int m[][]){
		try{
			lista.clear();
			Sorteio s;
			for (int i = 0; i < m.length; i++) {
				for (int j = 0; j < m[0].length; j++) {
					if(m[i][j] == 0){
						s = new Sorteio();
						s.setX(i);
						s.setY(j);
						lista.add(s);
					}
				}
			}			
			int aleatorio = (int)(Math.random()*lista.size());
			m[lista.get(aleatorio).getX()][lista.get(aleatorio).getY()] = 7;
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, "Você perdeu.");
			reset();
		}		
	}

	//*********MOVER PARA CIMA*********/
	public void eC(){
		for(int i = 0; i<m.length; i++){
			moverCima(0, i, m);			
		}
		somaCima(m);
		sorteio(m);
		for(int i = 0; i<m.length; i++){
			moverCima(0, i, m);			
		}
		refresh(false);
	}

	public int[][] moverCima(int x, int y, int m[][]){
		for (int j = 0; j < m[0].length; j++) {
			if(x < m.length-1){
				if(m[x][y] == 0){
				 m[x][y] = m[x+1][y];
				 m[x+1][y]= 0;
				}
				moverCima(x+1, y ,  m);				
			}
		}
		return m;
	}

	public void somaCima(int m[][]){
		for (int i = 0; i < m.length; i++) {
			for (int j = 1; j < m.length; j++) {
				if(m[j][i] == m[j-1][i]){
					m[j-1][i] = m[j][i] + m[j-1][i];
					m[j][i] = 0;
				}
			}
		}
	}
	//*********MOVER PARA CIMA*********/

	//*********MOVER PARA BAIXO*********/
	public void eB(){
		for(int i = 0; i<m.length; i++){
			moverBaixo(3, i, m);
		}
		somaBaixo(m);
		sorteio(m);		
		for(int i = 0; i<m.length; i++){
			moverBaixo(3, i, m);
		}
		refresh(false);
	}
	
	public int[][] moverBaixo(int x, int y, int m[][]){
		for (int j = 0; j < m[0].length; j++) {
			if(x > 0){
				if(m[x][y] == 0){
					 m[x][y] = m[x-1][y];
					 m[x-1][y]= 0;
					}
				moverBaixo(x-1, y ,  m);				
			}
		}
		return m;
	}

	public void somaBaixo(int m[][]){
		for (int i = 3; i >= 0; i--) {
			for (int j = 3; j > 0; j--) {
				if(m[j][i] == m[j-1][i]){
					m[j][i] = m[j][i] + m[j-1][i];
					m[j-1][i] = 0;
				}
			}
		}
	}
	
	//*********MOVER PARA BAIXO*********/

	//*********MOVER PARA DIREITA*********/
	public void eD(){
		for(int i = 0; i<m.length; i++){
			moverDireita(i, 3, m);
		}
		sorteio(m);
		somaDireita(m);
		for(int i = 0; i<m.length; i++){
			moverDireita(i, 3, m);
		}
		refresh(false);
	}

	public void moverDireita(int x, int y, int m[][]){
		for (int j = 0; j < m[0].length; j++) {
			if(y > 0){
				if(m[x][y] == 0){
					 m[x][y] = m[x][y-1] + m[x][y];
					 m[x][y-1]= 0;
					}
				moverDireita(x, y-1 ,  m);				
			}
		}
	}	
	
	public void somaDireita(int m[][]){
		for (int x = 3; x >= 0; x--) {
			for (int y = 3; y > 0; y--) {
				if(m[x][y] == m[x][y-1]){
					m[x][y] = m[x][y-1] + m[x][y];
					m[x][y-1] = 0; 
				}
			}
		}
	}
	
	//*********MOVER PARA DIREITA*********/

	//*********MOVER PARA ESQUERDA*********/
	public void eE(){
		for(int i = 0; i<m.length; i++){
			moverEsquerda(i, 0, m);
		}
		somaEsquerda(m);
		sorteio(m);
		for(int i = 0; i<m.length; i++){
			moverEsquerda(i, 0, m);
		}
		refresh(false);
	}

	public void moverEsquerda(int x, int y, int m[][]){
		for (int j = 0; j < m[0].length; j++) {
			if(y < m.length-1){
				if(m[x][y] == 0){
					 m[x][y] = m[x][y+1];
					 m[x][y+1]= 0;
					}
				moverEsquerda(x, y+1 ,  m);				
			}
		}
	}	

	public void somaEsquerda(int m[][]){
		for (int x = 0; x < m.length; x++) {
			for (int y = 0; y < m.length-1; y++) {
				if(m[x][y] == m[x][y+1]){
					m[x][y] = m[x][y] + m[x][y+1];
					m[x][y+1] = 0;					
				}
			}
		}
	}
	//*********MOVER PARA ESQUERDA*********/

	public void reset(){
		m = new int[][]{
				{0,0,0,0},
				{0,0,7,0},
				{0,7,0,0},
				{0,0,0,0}
				};
		refresh(false);
	}

	public void refresh(boolean isFirstTime){
		int x = 0;
		int y = 0;

		for (int i = 0; i < m[0].length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				if(isFirstTime){
					botoes[i][j] = new JButton();
					botoes[i][j].setBounds(x, y, 100, 100);
					botoes[i][j].setFont(new Font("Times New Roman", Font.PLAIN,40));
					contentPane.add(botoes[i][j]);
				}								
				if(m[i][j] != 0)
					botoes[i][j].setText(String.valueOf(m[i][j]));		
				if(m[i][j] == 0)
					botoes[i][j].setText("");		
				x+=100;
			}
			x = 0;
			y +=100;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyCode());
	}
}