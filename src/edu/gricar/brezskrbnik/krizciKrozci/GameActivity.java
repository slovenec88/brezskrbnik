package edu.gricar.brezskrbnik.krizciKrozci;

import java.util.Random;
import edu.gricar.brezskrbnik.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Handler.Callback;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.ConsoleMessage;
import android.widget.Button;
import android.widget.TextView;

import edu.gricar.brezskrbnik.krizciKrozci.GameView.ICellListener;
import edu.gricar.brezskrbnik.krizciKrozci.GameView.State;

public class GameActivity extends Activity {

	/** Start player. Must be 1 or 2. Default is 1. */
	public static final String EXTRA_START_PLAYER = "edu.gricar.brezskbrnik.krizciKrozci.GameActivity.EXTRA_START_PLAYER";

	private static final int MSG_COMPUTER_TURN = 1;
	private static final long COMPUTER_DELAY_MS = 1500;

	private Handler mHandler = new Handler(new MyHandlerCallback());
	private Random mRnd = new Random();
	private GameView mGameView;
	private TextView mInfoView;
	private Button mButtonNext;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.lib_game);

		mGameView = (GameView) findViewById(R.id.game_view);
		mInfoView = (TextView) findViewById(R.id.info_turn);
		mButtonNext = (Button) findViewById(R.id.next_turn);

		mGameView.setFocusable(true);
		mGameView.setFocusableInTouchMode(true);
		mGameView.setCellListener(new MyCellListener());

		mButtonNext.setOnClickListener(new MyButtonListener());
	}
	
	/*
	 * P - trenutni položaj igralec - igralec na potezi d - preostala globina
	 * alfa - spodnja meja ocene beta - zgornja meja ocene
	 */
	public int minimaks(int P, String igralec, int d, int alfa, int beta) {
		if (d == 0) {
			return 9;
			// preostala globina preiskovanja je 9
		}
		if (igralec.equalsIgnoreCase("MAX")) {
			alfa = -100;
		} else {
			beta = 100;
		}
		// nastavimo zgornjo oz spodnjo mejo ocene

		State[] data = mGameView.getData();
		// pogledamo na katerem polju imajo igralci postavljeno

		int ocena = 0;
		int naPotezi = 0;
		
		for (int i = 0; i < P; i++) {
			if (data[i] == State.EMPTY) {

				if (igralec.equalsIgnoreCase("MIN")) {
					// èe je igralec MIN
					ocena = hevristika("MAX", i);
					// vrnemo oceno hevristike
					if (ocena > alfa) {
						// pogledamo èe se splaèa postaviti tja
						alfa = ocena;
						naPotezi = i;
					}
				} else {
					ocena = hevristika("MIN", i);
					if (ocena < beta) {
						beta = ocena;
						naPotezi = i;
					}
				}
			}
			if (alfa >= beta)
				// režemo vejo v primeru da je alfa veèji od beta, torej se nam
				// se splaèa gledati naprej
				return naPotezi;
		}
		return naPotezi;
		/*
		 * izhod (ocena, poteza) vrednost minimaks drevesa in najboljša
		 * naslednja poteza
		 */
	}

	public int naPotezi() {
		int naPotezi = 0;
		String igralec;
		if (State.PLAYER1 == mGameView.getCurrentPlayer())
			igralec = "MAX";
		else
			igralec = "MIN";
		naPotezi = minimaks(9, igralec, 9, -100, 100);
		// vrnemo kam želi postaviti raèunalnik na polje
		return naPotezi;
	}

	public int hevristika(String igralec, int ind) {
		int krizec = 0;
		int krozec = 0;
		State[] data = mGameView.getData();
		if (igralec.equalsIgnoreCase("MAX")) {
			mGameView.setCell(ind, State.PLAYER1);
		} else {
			mGameView.setCell(ind, State.PLAYER2);
		}
		/*
		 * POLJE[0,1,2]
		 *  	[3,4,5] 
		 *  	[6,7,8]
		 */

		

		// diagonala levo proti desni
		if (data[0] == State.PLAYER1 || data[0] == State.EMPTY) {
			if ((data[4] == State.PLAYER1 || data[4] == State.EMPTY)) {
				if ((data[8] == State.PLAYER1 || data[8] == State.EMPTY)) {

					krizec++;
				}
			}
		}

		// diagonala desno proti levi
		if (data[2] == State.PLAYER1 || data[2] == State.EMPTY) {
			if (data[4] == State.PLAYER1 || data[4] == State.EMPTY) {
				if (data[6] == State.PLAYER1 || data[6] == State.EMPTY) {

					krizec++;
				}
			}
		}
		// prva vrstica
		if (data[0] == State.PLAYER1 || data[0] == State.EMPTY) {
			if (data[1] == State.PLAYER1 || data[1] == State.EMPTY) {
				if (data[2] == State.PLAYER1 || data[2] == State.EMPTY) {

					krizec++;
				}
			}
		}
		// druga
		if (data[3] == State.PLAYER1 || data[3] == State.EMPTY) {
			if (data[4] == State.PLAYER1 || data[4] == State.EMPTY) {
				if (data[5] == State.PLAYER1 || data[5] == State.EMPTY) {
					krizec++;
				}
			}
		}
		// tretja
		if (data[6] == State.PLAYER1 || data[6] == State.EMPTY) {
			if (data[7] == State.PLAYER1 || data[7] == State.EMPTY) {
				if (data[8] == State.PLAYER1 || data[8] == State.EMPTY) {
					krizec++;
				}
			}
		}
		// diagonala levo proti desni
		if (data[0] == State.PLAYER2 || data[0] == State.EMPTY) {
			if (data[4] == State.PLAYER2 || data[4] == State.EMPTY) {
				if (data[8] == State.PLAYER2 || data[8] == State.EMPTY) {
					krozec++;
				}
			}
		}
		// diagonala desno proti levi
		if (data[2] == State.PLAYER2 || data[2] == State.EMPTY) {
			if (data[4] == State.PLAYER2 || data[4] == State.EMPTY) {
				if (data[6] == State.PLAYER2 || data[6] == State.EMPTY) {
					krozec++;
				}
			}
		}
		// prva vrstica
		if (data[0] == State.PLAYER2 || data[0] == State.EMPTY) {
			if (data[1] == State.PLAYER2 || data[1] == State.EMPTY) {
				if (data[2] == State.PLAYER2 || data[2] == State.EMPTY) {
					krozec++;
				}
			}
		}
		// druga vrstica
		if (data[3] == State.PLAYER2 || data[3] == State.EMPTY) {
			if (data[4] == State.PLAYER2 || data[4] == State.EMPTY) {
				if (data[5] == State.PLAYER2 || data[5] == State.EMPTY) {
					krozec++;
				}
			}
		}
		// tretja vrstica
		if (data[6] == State.PLAYER2 || data[6] == State.EMPTY) {
			if (data[7] == State.PLAYER2 || data[7] == State.EMPTY) {
				if (data[8] == State.PLAYER2 || data[8] == State.EMPTY) {
					krozec++;
				}
			}
		}
		
		// stolpci gledam dol, kateri ima prednost
		if (data[0] == State.PLAYER1 || data[0] == State.EMPTY) {
			if (data[3] == State.PLAYER1 || data[3] == State.EMPTY) {
				if (data[6] == State.PLAYER1 || data[6] == State.EMPTY) {
					krizec++;
				}
			}
		}

		if (data[1] == State.PLAYER1 || data[1] == State.EMPTY) {
			if (data[4] == State.PLAYER1 || data[4] == State.EMPTY) {
				if (data[7] == State.PLAYER1 || data[7] == State.EMPTY) {
					krizec++;
				}
			}
		}

		if (data[2] == State.PLAYER1 || data[2] == State.EMPTY) {
			if (data[5] == State.PLAYER1 || data[5] == State.EMPTY) {
				if (data[8] == State.PLAYER1 || data[8] == State.EMPTY) {
					krizec++;
				}
			}
		}

		if (data[0] == State.PLAYER2 || data[0] == State.EMPTY) {
			if (data[3] == State.PLAYER2 || data[3] == State.EMPTY) {
				if (data[6] == State.PLAYER2 || data[6] == State.EMPTY) {
					krozec++;
				}
			}
		}

		if (data[1] == State.PLAYER2 || data[1] == State.EMPTY) {
			if (data[4] == State.PLAYER2 || data[4] == State.EMPTY) {
				if (data[7] == State.PLAYER2 || data[7] == State.EMPTY) {
					krozec++;
				}
			}
		}

		if (data[2] == State.PLAYER2 || data[2] == State.EMPTY) {
			if (data[5] == State.PLAYER2 || data[5] == State.EMPTY) {
				if (data[8] == State.PLAYER2 || data[8] == State.EMPTY) {
					krozec++;
				}
			}
		}
		
		mGameView.setCell(ind, State.EMPTY);
		return krizec - krozec;

	}

	@Override
	protected void onResume() {
		super.onResume();

		State player = mGameView.getCurrentPlayer();
		if (player == State.UNKNOWN) {
			player = State.fromInt(getIntent().getIntExtra(EXTRA_START_PLAYER,
					1));
			if (!checkGameFinished(player)) {
				selectTurn(player);
			}
		}
		if (player == State.PLAYER2) {
			mHandler.sendEmptyMessageDelayed(MSG_COMPUTER_TURN,
					COMPUTER_DELAY_MS);
		}
		if (player == State.WIN) {
			setWinState(mGameView.getWinner());
		}
	}

	private State selectTurn(State player) {
		mGameView.setCurrentPlayer(player);
		mButtonNext.setEnabled(false);

		if (player == State.PLAYER1) {
			mInfoView.setText(R.string.player1_turn);
			mGameView.setEnabled(true);

		} else if (player == State.PLAYER2) {
			mInfoView.setText(R.string.player2_turn);
			mGameView.setEnabled(false);
		}

		return player;
	}

	private class MyCellListener implements ICellListener {
		public void onCellSelected() {
			if (mGameView.getCurrentPlayer() == State.PLAYER1) {
				int cell = mGameView.getSelection();
				mButtonNext.setEnabled(cell >= 0);
			}
		}
	}

	private class MyButtonListener implements OnClickListener {

		public void onClick(View v) {
			State player = mGameView.getCurrentPlayer();

			if (player == State.WIN) {
				GameActivity.this.finish();

			} else if (player == State.PLAYER1) {
				int cell = mGameView.getSelection();
				if (cell >= 0) {
					mGameView.stopBlink();
					mGameView.setCell(cell, player);
					finishTurn();
				}
			}
		}
	}

	private class MyHandlerCallback implements Callback {
		public boolean handleMessage(Message msg) {
			if (msg.what == MSG_COMPUTER_TURN) {
				// Pick a non-used cell at random. That's about all the AI you
				// need for this game.
				State[] data = mGameView.getData();
				int used = 0;
				while (used != 0x1F) {
					int a = naPotezi();
					int index = a;
					if (((used >> index) & 1) == 0) {
						used |= 1 << index;
						if (data[index] == State.EMPTY) {
							mGameView.setCell(index,
									mGameView.getCurrentPlayer());
							break;
						}
					}
				}

				finishTurn();
				return true;
			}
			return false;
		}
	}

	

	private State getOtherPlayer(State player) {
		return player == State.PLAYER1 ? State.PLAYER2 : State.PLAYER1;
	}

	private void finishTurn() {
		State player = mGameView.getCurrentPlayer();
		if (!checkGameFinished(player)) {
			player = selectTurn(getOtherPlayer(player));
			if (player == State.PLAYER2) {
				mHandler.sendEmptyMessageDelayed(MSG_COMPUTER_TURN,
						COMPUTER_DELAY_MS);
			}
		}
	}

	public boolean checkGameFinished(State player) {
		State[] data = mGameView.getData();
		boolean full = true;

		int col = -1;
		int row = -1;
		int diag = -1;

		// check rows
		for (int j = 0, k = 0; j < 3; j++, k += 3) {
			if (data[k] != State.EMPTY && data[k] == data[k + 1]
					&& data[k] == data[k + 2]) {
				row = j;
			}
			if (full
					&& (data[k] == State.EMPTY || data[k + 1] == State.EMPTY || data[k + 2] == State.EMPTY)) {
				full = false;
			}
		}

		// check columns
		for (int i = 0; i < 3; i++) {
			if (data[i] != State.EMPTY && data[i] == data[i + 3]
					&& data[i] == data[i + 6]) {
				col = i;
			}
		}

		// check diagonals
		if (data[0] != State.EMPTY && data[0] == data[1 + 3]
				&& data[0] == data[2 + 6]) {
			diag = 0;
		} else if (data[2] != State.EMPTY && data[2] == data[1 + 3]
				&& data[2] == data[0 + 6]) {
			diag = 1;
		}

		if (col != -1 || row != -1 || diag != -1) {
			setFinished(player, col, row, diag);
			return true;
		}

		// if we get here, there's no winner but the board is full.
		if (full) {
			setFinished(State.EMPTY, -1, -1, -1);
			return true;
		}
		return false;
	}

	private void setFinished(State player, int col, int row, int diagonal) {

		mGameView.setCurrentPlayer(State.WIN);
		mGameView.setWinner(player);
		mGameView.setEnabled(false);
		mGameView.setFinished(col, row, diagonal);

		setWinState(player);
	}

	private void setWinState(State player) {
		mButtonNext.setEnabled(true);
		mButtonNext.setText("Nazaj");

		String text;

		if (player == State.EMPTY) {
			text = getString(R.string.tie);
		} else if (player == State.PLAYER1) {
			text = getString(R.string.player1_win);
		} else {
			text = getString(R.string.player2_win);
		}
		mInfoView.setText(text);
	}
}