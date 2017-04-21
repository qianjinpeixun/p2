
/*
 * This demo class uses java FX to simulate an ATM
 */
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

	// 0 is cheque, 1 is saving, this variable to indicate current account type
	private int currentAccountType = 0;
	// this variable is for get current login user
	private User currentUser = null;
	// 0 is deposit, 1 is withdraw
	// after selecting deposit or withdraw, this varaible is for storing the
	// flag
	private int currentOperationType = 0;

	// definition about size
	private int button_width = 100;
	private int button_height = 100;
	private String button_style = "-fx-font:18 Arial";
	private String window_title = "Welcome to NOVA Bank";

	// public variables
	Font font_login = Font.font("Arial", FontWeight.BOLD, 14);
	Font font_transfer = Font.font("Arial", FontWeight.BOLD, 12);
	Pane currentPane;
	Stage currentStage;

	// different panes
	private GridPane loginPane = new GridPane();
	// this is for select cheque or saving page
	private BorderPane accountPane = new BorderPane();
	// this is for main page, show deposit/withdraw/transfer and other menus
	private BorderPane mainPagePane = new BorderPane();
	// this is for amount page: cad 5 10 20 100 200
	private BorderPane detailPane = new BorderPane();
	// this is for transfer function, to show receipt number and amount
	private GridPane transferPan = new GridPane();

	// account page variables
	private Button button_account_cheque;
	private Button button_account_saving;
	private Button button_logout;

	// detail amount page varaiables
	private Button button_5;
	private Button button_10;
	private Button button_20;
	private Button button_100;
	private Button button_200;
	private Button button_detail_back;

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
	private Label label_main_message;
	private Label label_transfer_number;
	private TextField text_transfer_number;
	private Label label_transfer_amount;
	private TextField text_transfer_amount;
	private Label label_transfer_message;
	private Button button_transfer_ok;
	private Label label_message;

	// deposit variables
	private Label label_deposite;
	private TextField text_deposit;
	private Button button_deposit;

	@Override
	public void start(Stage primaryStage) {
		try {
			BankDatabase.loadData();
			this.currentStage = primaryStage;
			showLoginPage();
			// if you want to change the default window size, modify the below
			Scene scene = new Scene(currentPane, 700, 400);
			primaryStage.setScene(scene);
			primaryStage.setTitle(window_title);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// after select transfer function, this method will be called
	public void showTransferPane() {

		label_transfer_number = new Label("Receipt: ");
		label_transfer_amount = new Label("Amount: ");
		label_transfer_number.setFont(font_transfer);
		label_transfer_amount.setFont(font_transfer);
		button_transfer_ok = new Button("Transfer");
		button_transfer_ok.setStyle(button_style);

		label_transfer_message = new Label("");
		label_transfer_message.setFont(font_transfer);
		label_transfer_message.setVisible(false);
		button_transfer_ok.setOnAction(this::processTransferButtonPress);

		text_transfer_number = new TextField();
		text_transfer_number.setText("001");
		text_transfer_amount = new TextField();
		text_transfer_amount.setText("1000");

		transferPan.setAlignment(Pos.CENTER);
		transferPan.setPadding(new Insets(20, 20, 20, 20));
		transferPan.setHgap(20);
		transferPan.setVgap(10);
		transferPan.setStyle("-fx-background-color:PINK");
		transferPan.add(label_transfer_number, 0, 0);
		transferPan.add(text_transfer_number, 1, 0);
		transferPan.add(label_transfer_amount, 0, 2);
		transferPan.add(text_transfer_amount, 1, 2);
		transferPan.add(button_transfer_ok, 1, 4);
		transferPan.add(label_transfer_message, 0, 5);

	}

	// show the login page
	public void showLoginPage() {
		label_login_name = new Label("User number: ");
		label_login_password = new Label("Password ");
		label_login_name.setFont(font_login);
		label_login_password.setFont(font_login);
		button_login = new Button("Login");
		button_login.setStyle(button_style);

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

	// when select deposit or withdraw, show the detail money page
	public void showDetailPage() {

		button_5 = new Button("5");
		button_10 = new Button("10");
		button_20 = new Button("20");
		button_100 = new Button("100");
		button_200 = new Button("200");
		button_detail_back = new Button("Back");

		button_5.setStyle(button_style);
		button_10.setStyle(button_style);
		button_20.setStyle(button_style);
		button_100.setStyle(button_style);
		button_200.setStyle(button_style);
		button_detail_back.setStyle(button_style);

		button_5.setPrefWidth(button_width);
		button_5.setPrefHeight(button_height);
		button_10.setPrefWidth(button_width);
		button_10.setPrefHeight(button_height);
		button_20.setPrefWidth(button_width);
		button_20.setPrefHeight(button_height);
		button_100.setPrefWidth(button_width);
		button_100.setPrefHeight(button_height);
		button_200.setPrefWidth(button_width);
		button_200.setPrefHeight(button_height);
		button_detail_back.setPrefWidth(button_width);
		button_detail_back.setPrefHeight(button_height);

		button_5.setOnAction(this::processDetailButton);
		button_10.setOnAction(this::processDetailButton);
		button_20.setOnAction(this::processDetailButton);
		button_100.setOnAction(this::processDetailButton);
		button_200.setOnAction(this::processDetailButton);
		button_detail_back.setOnAction(this::processDetailButton);

		label_message = new Label("");
		label_message.setFont(font_login);
		label_message.setVisible(false);
		detailPane.setCenter(label_message);

		VBox left = new VBox();
		left.setSpacing(20);
		left.setAlignment(Pos.CENTER);
		left.setPadding(new Insets(0, 20, 10, 20));
		left.getChildren().addAll(button_5, button_10, button_20);
		VBox right = new VBox();
		right.setSpacing(20);
		right.setAlignment(Pos.CENTER);
		right.setPadding(new Insets(0, 20, 10, 20));
		right.getChildren().addAll(button_100, button_200, button_detail_back);

		detailPane.setLeft(left);
		detailPane.setRight(right);
		detailPane.setStyle("-fx-background-color:PINK");
		detailPane.setPadding(new Insets(20, 20, 20, 20));

		currentPane = detailPane;
		currentStage.getScene().setRoot(currentPane);
	}

	// after login, show the cheque or saving account page
	public void showAccountPage() {
		button_account_cheque = new Button("Cheque");
		button_account_saving = new Button("Saving");
		button_logout = new Button("Logout");
		button_account_cheque.setStyle(button_style);
		button_account_saving.setStyle(button_style);
		button_logout.setStyle(button_style);
		button_account_cheque.setOnAction(this::processAccountButtonCheque);
		button_account_saving.setOnAction(this::processAccountButtonSaving);
		button_logout.setOnAction(this::processAccountButtonLogout);
		button_account_cheque.setPrefWidth(button_width);
		button_account_cheque.setPrefHeight(button_height);
		button_account_saving.setPrefWidth(button_width);
		button_account_saving.setPrefHeight(button_height);
		button_logout.setPrefWidth(button_width);
		button_logout.setPrefHeight(button_height);

		VBox left = new VBox();
		left.setSpacing(20);
		left.setAlignment(Pos.TOP_CENTER);
		left.setPadding(new Insets(0, 20, 10, 20));
		left.getChildren().addAll(button_account_cheque, button_account_saving, button_logout);

		accountPane.setLeft(left);
		accountPane.setStyle("-fx-background-color:PINK");
		accountPane.setPadding(new Insets(20, 20, 20, 20));
		currentPane = accountPane;
		currentStage.getScene().setRoot(currentPane);

	}

	// after select cheque or saving, show the mainPage
	public void showMainPage() {
		button_menu_show_balance = new Button("Balance");
		button_menu_show_back = new Button("Back");
		button_menu_show_deposit = new Button("Deposit");
		button_menu_show_withdraw = new Button("Withdraw");
		button_menu_show_transfer = new Button("Transfer");
		button_menu_show_balance.setStyle(button_style);
		button_menu_show_deposit.setStyle(button_style);
		button_menu_show_withdraw.setStyle(button_style);
		button_menu_show_transfer.setStyle(button_style);
		button_menu_show_back.setStyle(button_style);
		button_menu_show_balance.setOnAction(this::processMainButtonBalance);
		button_menu_show_deposit.setOnAction(this::processMainButtonDeposit);
		button_menu_show_withdraw.setOnAction(this::processMainButtonWithdraw);
		button_menu_show_transfer.setOnAction(this::processMainButtonTransfer);
		button_menu_show_back.setOnAction(this::processMainButtonBack);

		button_menu_show_deposit.setPrefWidth(button_width);
		button_menu_show_deposit.setPrefHeight(button_height);
		button_menu_show_withdraw.setPrefWidth(button_width);
		button_menu_show_withdraw.setPrefHeight(button_height);
		button_menu_show_balance.setPrefWidth(button_width);
		button_menu_show_balance.setPrefHeight(button_height);
		button_menu_show_transfer.setPrefWidth(button_width);
		button_menu_show_transfer.setPrefHeight(button_height);
		button_menu_show_back.setPrefWidth(button_width);
		button_menu_show_back.setPrefHeight(button_height);

		VBox left = new VBox();
		left.setSpacing(20);
		left.setAlignment(Pos.CENTER);
		left.setPadding(new Insets(0, 20, 10, 20));
		left.getChildren().addAll(button_menu_show_deposit, button_menu_show_withdraw, button_menu_show_balance);
		VBox right = new VBox();
		right.setSpacing(20);
		right.setAlignment(Pos.CENTER);
		right.setPadding(new Insets(0, 20, 10, 20));
		Button temp = new Button();
		temp.setText("");
		temp.setStyle(button_style);
		temp.setPrefHeight(button_height);
		temp.setPrefWidth(button_width);
		right.getChildren().addAll(button_menu_show_transfer, button_menu_show_back, temp);

		label_main_message = new Label("");
		label_main_message.setFont(font_login);
		label_main_message.setVisible(false);
		mainPagePane.setCenter(label_main_message);

		mainPagePane.setLeft(left);
		mainPagePane.setRight(right);
		mainPagePane.setStyle("-fx-background-color:PINK");
		mainPagePane.setPadding(new Insets(20, 20, 20, 20));

		currentPane = mainPagePane;
		currentStage.getScene().setRoot(currentPane);

	}

	// show the current account balance
	public void processMainButtonBalance(ActionEvent event) {
		String balance = "the current balance is:";
		if (currentAccountType == 0) {
			balance = balance + currentUser.getChque().getBalance();
		} else if (currentAccountType == 1) {
			balance = balance + currentUser.getSaving().getBalance();
		}
		mainPagePane.setCenter(label_main_message);
		label_main_message.setText(balance);
		label_main_message.setVisible(true);
		System.out.println("dddd");
	}

	// begin to deposit
	public void processMainButtonDeposit(ActionEvent event) {
		System.out.println("dddd");
		currentOperationType = 0;
		showDetailPage();
	}

	// begin to withdraw
	public void processMainButtonWithdraw(ActionEvent event) {
		System.out.println("dddd");
		currentOperationType = 1;
		showDetailPage();
	}

	// begin to transfer
	public void processMainButtonTransfer(ActionEvent event) {
		System.out.println("dddd");
		label_main_message.setVisible(false);
		showTransferPane();
		mainPagePane.setCenter(transferPan);
	}

	// back to account page
	public void processMainButtonBack(ActionEvent event) {
		System.out.println("Back");
		showAccountPage();
	}

	// begin to login, check if the userNumber and password is correct
	public void processLoginButtonPress(ActionEvent event) {
		System.out.println("dddd");
		String number = text_login_name.getText();
		int password = Integer.parseInt(text_login_password.getText());
		String ret = BankDatabase.login(number, password);
		System.out.println("long ret=" + ret);
		if (ret != null && ret.equals("ok")) {
			label_login_message.setText("");
			label_login_message.setVisible(false);
			showAccountPage();
			currentUser = BankDatabase.getUserByUserNumber(number);
			System.out.println("currentUser=" + currentUser);
		} else {
			label_login_message.setText(ret);
			label_login_message.setVisible(true);
		}
	}

	//if select cheque account, currentAccountType is 0
	public void processAccountButtonCheque(ActionEvent event) {
		System.out.println("deposit");
		currentAccountType = 0;
		showMainPage();
	}

	//if select saving account, currentAccountType is 1
	public void processAccountButtonSaving(ActionEvent event) {
		currentAccountType = 1;
		showMainPage();
	}

	//if select one amount for deposit or withdraw
	public void processDetailButton(ActionEvent event) {

		double amount = 0;
		String ret = "";
		if (event.getSource() == button_5) {
			System.out.println("555");
			ret = operate(5);
		} else if (event.getSource() == button_10) {
			System.out.println("10");
			ret = operate(10);
		} else if (event.getSource() == button_20) {
			System.out.println("20");
			ret = operate(20);
		} else if (event.getSource() == button_100) {
			System.out.println("100");
			ret = operate(100);
		} else if (event.getSource() == button_200) {
			System.out.println("200");
			ret = operate(200);
		} else {
			System.out.println("back");
			showMainPage();
		}

	}

	//begin to deposit or withdraw money to current user account
	private String operate(double amount) {
		String ret = "";
		if (currentAccountType == 0) {
			if (currentOperationType == 0)
				ret = currentUser.getChque().deposit(amount);
			else if (currentOperationType == 1)
				ret = currentUser.getChque().withdraw(amount);
		} else if (currentAccountType == 1) {
			if (currentOperationType == 0)
				ret = currentUser.getSaving().deposit(amount);
			else if (currentOperationType == 1)
				ret = currentUser.getSaving().withdraw(amount);
		}
		label_message.setVisible(true);
		if (ret.equals("ok")) {
			label_message.setText("Success!");
		} else {
			label_message.setText(ret);
		}
		return ret;
	}

	//begin to transfer the money to receipt
	public void processTransferButtonPress(ActionEvent event) {
		String accountNumber = text_transfer_number.getText();
		String ret = "";
		double amount = Double.parseDouble(text_transfer_amount.getText());
		System.out.println("accountNumber=" + accountNumber + ",amount=" + amount + ",currentAccountType=" + currentAccountType);
		if (amount < 0.0) {
			ret = "amount must be more than zero!";
		} else {
			if (currentAccountType == 0) {
				ret = currentUser.getChque().transfer(accountNumber, amount);
			} else if (currentAccountType == 1) {
				ret = currentUser.getSaving().transfer(accountNumber, amount);
			}
		}
		if (ret.equals("ok")) {
			label_transfer_message.setText("Success");
		} else {
			label_transfer_message.setText(ret);
		}
		label_transfer_message.setVisible(true);
		mainPagePane.setCenter(label_transfer_message);
	}

	//if logout, save the data into local file
	public void processAccountButtonLogout(ActionEvent event) {
		BankDatabase.saveDate();
		showLoginPage();
		currentStage.getScene().setRoot(currentPane);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
