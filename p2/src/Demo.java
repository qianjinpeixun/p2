
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Demo extends Application {

	// public variables
	Font font_login = Font.font("Arial", FontWeight.BOLD, 14);
	Pane currentPane;
	Stage currentStage;
	private GridPane loginPane = new GridPane();
	private BorderPane mainPagePane = new BorderPane();

	// login page variables
	private Label label_login_name;
	private Label label_login_password;
	private TextField text_login_name;
	private TextField text_login_password;
	private Button button_login;
	private Label label_login_message;

	// main page variables
	private Button button_menu_show_balance;
	private Button button_menu_show_deposit;
	private Button button_menu_show_withdraw;
	private Button button_menu_show_transfer;
	private Button button_menu_show_back;

	// show balance variables
	private Label label_show_balance;

	// deposit variables
	private Label label_deposite;
	private TextField text_deposit;
	private Button button_deposit;

	@Override
	public void start(Stage primaryStage) {
		try {
			BankDatabase.loadData();
			this.currentStage = primaryStage;
			// BorderPane root = new BorderPane();
			showLoginPage();
			Scene scene = new Scene(currentPane, 600, 400);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Welcome to NOVA Bank");

			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showLoginPage() {
		label_login_name = new Label("Account number: ");
		label_login_password = new Label("Password ");
		label_login_name.setFont(font_login);
		label_login_password.setFont(font_login);
		button_login = new Button("Submit");
		button_login.setStyle("-fx-font:20 Arial");

		label_login_message = new Label("");
		label_login_message.setFont(font_login);
		label_login_message.setVisible(false);
		button_login.setOnAction(this::processLoginButtonPress);

		text_login_name = new TextField();
		text_login_name.setText("1234567891");
		text_login_password = new TextField();
		text_login_password.setText("1234");
		loginPane.setAlignment(Pos.CENTER);
		loginPane.setPadding(new Insets(20, 20, 20, 20));
		loginPane.setHgap(20);
		loginPane.setVgap(10);
		loginPane.setStyle("-fx-background-color:PINK");
		loginPane.add(label_login_name, 0, 0);
		loginPane.add(text_login_name, 1, 0);
		loginPane.add(label_login_password, 0, 2);
		loginPane.add(text_login_password, 1, 2);
		loginPane.add(button_login, 1, 4);
		loginPane.add(label_login_message, 0, 5);
		currentPane = loginPane;
	}

	public void hideLoginPage() {
		currentPane = mainPagePane;
		currentStage.getScene().setRoot(currentPane);
	}

	public void hideMainPage() {
		currentPane = loginPane;
		currentStage.getScene().setRoot(currentPane);
	}

	public void showMainPage() {
		button_menu_show_balance = new Button("Balance");
		button_menu_show_back = new Button("Back");
		button_menu_show_deposit = new Button("Deposit");
		button_menu_show_withdraw = new Button("Withdraw");
		button_menu_show_transfer = new Button("Transfer");
		button_menu_show_balance.setStyle("-fx-font:20 Arial");
		button_menu_show_deposit.setStyle("-fx-font:20 Arial");
		button_menu_show_withdraw.setStyle("-fx-font:20 Arial");
		button_menu_show_transfer.setStyle("-fx-font:20 Arial");
		button_menu_show_back.setStyle("-fx-font:20 Arial");
		button_menu_show_balance.setOnAction(this::processMainButtonBalance);
		button_menu_show_deposit.setOnAction(this::processMainButtonDeposit);
		button_menu_show_withdraw.setOnAction(this::processMainButtonWithdraw);
		button_menu_show_transfer.setOnAction(this::processMainButtonTransfer);
		button_menu_show_back.setOnAction(this::processMainButtonBack);

		button_menu_show_deposit.setPrefWidth(120);
		button_menu_show_deposit.setPrefHeight(120);
		button_menu_show_withdraw.setPrefWidth(120);
		button_menu_show_withdraw.setPrefHeight(120);
		button_menu_show_balance.setPrefWidth(120);
		button_menu_show_balance.setPrefHeight(120);
		button_menu_show_transfer.setPrefWidth(120);
		button_menu_show_transfer.setPrefHeight(120);
		button_menu_show_back.setPrefWidth(120);
		button_menu_show_back.setPrefHeight(120);
		
		VBox left = new VBox();
		left.setSpacing(20);
		left.setAlignment(Pos.CENTER);
		left.setPadding(new Insets(0, 20, 10, 20)); 
		left.getChildren().addAll(button_menu_show_deposit, button_menu_show_withdraw, button_menu_show_balance);
		VBox right = new VBox();
		right.setSpacing(20);
		right.setAlignment(Pos.CENTER);
		right.setPadding(new Insets(0, 20, 10, 20)); 
		Button temp=new Button();
		temp.setText("");
		temp.setStyle("-fx-font:20 Arial");
		temp.setPrefHeight(120);
		temp.setPrefWidth(120);
		right.getChildren().addAll(button_menu_show_transfer, button_menu_show_back,temp);
		
		mainPagePane.setLeft(left);
		mainPagePane.setRight(right);
		mainPagePane.setStyle("-fx-background-color:PINK");
		mainPagePane.setPadding(new Insets(20, 20, 20, 20));
		
		// mainPagePane.setAlignment(Pos.CENTER_LEFT);
		// mainPagePane.setPadding(new Insets(20, 20, 20, 20));
		// mainPagePane.setHgap(20);
		// mainPagePane.setVgap(10);
		// mainPagePane.setStyle("-fx-background-color:PINK");
		//
		// ColumnConstraints column1 = new ColumnConstraints();
		// column1.setPercentWidth(30);
		// ColumnConstraints column2 = new ColumnConstraints();
		// column1.setPercentWidth(40);
		// ColumnConstraints column3 = new ColumnConstraints();
		// column1.setPercentWidth(40);
		// mainPagePane.getColumnConstraints().addAll(column1, column2,
		// column3);
		//
		// mainPagePane.add(button_menu_show_balance, 0, 0);
		// mainPagePane.add(button_menu_show_deposit, 0, 1);
		// mainPagePane.add(button_menu_show_withdraw, 0, 2);
		// mainPagePane.add(button_menu_show_transfer, 0, 3);

		// begin to init the show balance page
//		label_show_balance = new Label("");
//		label_show_balance.setFont(font_login);
//		label_show_balance.setVisible(false);
//		mainPagePane.add(label_show_balance, 1, 1);

		// begin init the deposit page
//		label_deposite = new Label("");
//		label_deposite.setFont(font_login);
//		label_deposite.setVisible(false);
//		text_deposit = new TextField();
//		text_deposit.setVisible(false);
//		button_deposit = new Button("Deposit");
//		button_deposit.setStyle("-fx-font:20 Arial");
//		button_deposit.setOnAction(this::processDepositButtonPress);
//		button_deposit.setVisible(false);
//		mainPagePane.add(text_deposit, 1, 1);
//		mainPagePane.add(button_deposit, 1, 2);
//		mainPagePane.add(label_deposite, 1, 3);

		currentPane = mainPagePane;
		currentStage.getScene().setRoot(currentPane);

	}

	public void initPage() {

	}

	public void showMainPageBalance() {

	}

	public void showMainPageDeposit() {

		label_deposite.setVisible(true);
		text_deposit.setVisible(true);
		button_deposit.setVisible(true);

		// hide others
		label_show_balance.setVisible(false);

	}

	public void showMainPageWithdraw() {

	}

	public void showMainPageTransfer() {

	}

	public void processMainButtonBalance(ActionEvent event) {
		String balance = "the current balance is: 99999";
		label_show_balance.setText(balance);
		label_show_balance.setVisible(true);
		System.out.println("dddd");
		showMainPageBalance();
	}

	public void processMainButtonDeposit(ActionEvent event) {
		System.out.println("dddd");

		showMainPageDeposit();
	}

	public void processMainButtonWithdraw(ActionEvent event) {
		System.out.println("dddd");
		showMainPageWithdraw();
	}

	public void processMainButtonTransfer(ActionEvent event) {
		System.out.println("dddd");
		showMainPageTransfer();
	}

	public void processMainButtonBack(ActionEvent event) {
		System.out.println("Back");
		showMainPageTransfer();
	}

	public void processLoginButtonPress(ActionEvent event) {
		System.out.println("dddd");
		String number = text_login_name.getText();
		int password = Integer.parseInt(text_login_password.getText());
		String ret = BankDatabase.login(number, password);
		System.out.println("long ret=" + ret);
		if (ret != null && ret.equals("ok")) {
			hideLoginPage();
			showMainPage();
		} else {
			label_login_message.setText(ret);
			label_login_message.setVisible(true);
		}
	}

	public void processDepositButtonPress(ActionEvent event) {

		System.out.println("deposit");
		label_deposite.setText("Deposit successfully,999");
		label_deposite.setVisible(true);

	}

	public static void main(String[] args) {
		launch(args);
	}
}
