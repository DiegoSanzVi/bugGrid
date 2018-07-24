package org.vaadin.diego;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.Route;

import java.util.Set;

/**
 * The main view contains a button and a template element.
 */
@HtmlImport("styles/shared-styles.html")
@Route("")
public class MainView extends VerticalLayout {

    private SplitLayout splitLayout;

    private Grid<Person> peopleGrid;
    private VerticalLayout verticalLayout;

    private static final String BACK_GROUND_COLOR = "whitesmoke";

    public MainView() {
        setWidth("100%"); getStyle().set("background-color",BACK_GROUND_COLOR).set("min-height","100%"); setPadding(false);

        peopleGrid = new Grid<>();

        peopleGrid.addColumn(Person::getName).setHeader("Name");
        peopleGrid.addColumn(Person::getAge).setHeader("Age");


        peopleGrid.addSelectionListener( event -> {
            Set<Person> reports = event.getSource().getSelectedItems();
            if ( !reports.isEmpty() ){
                showSecondElement();
            }else{
                hideSecondElement();
            }
        });
        peopleGrid.setItems( Person.getPeople() );

        verticalLayout = new VerticalLayout(); 	verticalLayout.setHeight("400px");
        verticalLayout.setWidth("100%"); verticalLayout.setPadding(true); verticalLayout.getStyle().set("padding","8px"); verticalLayout.add(new H1("Second element"));

        splitLayout = new SplitLayout(); splitLayout.setOrientation(SplitLayout.Orientation.VERTICAL);
        splitLayout.addToPrimary(peopleGrid);
        splitLayout.setWidth("100%");splitLayout.getStyle().set("min-height","300px	").set("margin-top","0px").set("background-color","whitesmoke");

        add(splitLayout);
    }

    /**
     * Show the selected report in the split layout under the report table.
     *
     */
    public void showSecondElement(){
        if ( splitLayout.getSecondaryComponent() != verticalLayout ){
            splitLayout.addToSecondary( verticalLayout );
        }
        verticalLayout.getElement().getStyle().set("display","flex");
    }

    /**
     * Hide the vertical layout
     */
    public void hideSecondElement(){
        splitLayout.addToSecondary(new Div()); // avoiding bugs
    }
}
