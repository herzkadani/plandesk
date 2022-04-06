package ch.bbzbl.plandesk.views.desk;

import java.util.List;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;

import ch.bbzbl.plandesk.bl.desk.BoardLogic;
import ch.bbzbl.plandesk.dto.desk.BoardDto;
import ch.bbzbl.plandesk.dto.desk.MitarbeiterDto;
import ch.bbzbl.plandesk.dto.desk.SpaltenDto;
import ch.bbzbl.plandesk.dto.desk.VorgangDto;
import ch.bbzbl.plandesk.views.dialog.CreateProcessDialog;
import ch.bbzbl.plandesk.views.dialog.EditProcessDialog;

/**
 * main class of the GUI
 * @author Dani Herzka
 *
 */
@PWA(name = "PlanDesk", shortName = "PlanDesk", enableInstallPrompt = false)
@Theme(themeFolder = "plandesk")
@PageTitle("Desk")
@Route(value = "")
@CssImport("./themes/plandesk/styles.css")
public class DeskView extends VerticalLayout {

	private static final long serialVersionUID = -7532896783327421334L;

	// Components
	private VerticalLayout layBoard;
	private HorizontalLayout layHeader;
	private HorizontalLayout layWrapperAddProcess;
	private HorizontalLayout layColumns;
	private H2 txtBoardTitle;
	private Button btnAddProcess;

	// Members
	List<MitarbeiterDto> users;
	private BoardLogic boardLogic;

	/**
	 * Constructor
	 */
	public DeskView() {
		// init the board
		boardLogic = new BoardLogic();
		boardLogic.initBoard();

		layBoard = new VerticalLayout();
	}

	/**
	 * initialize the user interface
	 */
	@PostConstruct
	private void initUi() {
		if (layBoard.getChildren().findAny().isPresent())
			layBoard.removeAll();
		layBoard.setSizeFull();

		BoardDto board = boardLogic.getBoard();
		users = board.getMitarbeiter();

		layHeader = new HorizontalLayout();
		layHeader.setWidthFull();

		txtBoardTitle = new H2(board.getName());
		txtBoardTitle.getStyle().set("margin-top", "0");

		btnAddProcess = new Button("Vorgang erstellen", new Icon(VaadinIcon.PLUS));
		btnAddProcess.addClickListener(e -> createNewProcess());

		layWrapperAddProcess = new HorizontalLayout();
		layWrapperAddProcess.setWidthFull();
		layWrapperAddProcess.setJustifyContentMode(JustifyContentMode.END);
		layWrapperAddProcess.add(btnAddProcess);
		layHeader.setWidthFull();

		layHeader.add(txtBoardTitle, layWrapperAddProcess);

		layColumns = new HorizontalLayout();
		layColumns.setHeight("90%");
		layColumns.setWidthFull();

		
		//load data
		for (SpaltenDto spalte : board.getSpalten()) {
			ColumnDiv columnDiv = new ColumnDiv(spalte.getName());
			layColumns.add(columnDiv);
			for (VorgangDto vorgang : spalte.getVorgaenge()) {
				ProcessDiv processDiv = new ProcessDiv(vorgang.getTitel(), vorgang.getMitarbeiter(),
						vorgang.getDringlichkeit(), vorgang.getType());
				processDiv.addClickListener(event -> openProcess(vorgang.getID()));
				columnDiv.add(processDiv);
			}
		}

		layBoard.add(layHeader, layColumns);

		setSpacing(true);
		setPadding(true);
		setSizeFull();
		setJustifyContentMode(JustifyContentMode.CENTER);
		setAlignItems(Alignment.END);

		if (getChildren().findAny().isEmpty())
			add(layBoard);
	}

	/**
	 * open an existing process by the process id
	 * 
	 * @param processId the id of the process
	 */
	private void openProcess(int processId) {
		EditProcessDialog dialog = new EditProcessDialog(processId, boardLogic, e->initUi());
		dialog.open();
	}

	
	
	/**
	 * create a new process
	 */
	private void createNewProcess() {
		CreateProcessDialog dialog = new CreateProcessDialog(boardLogic, e->initUi());
		dialog.open();

	}

}
