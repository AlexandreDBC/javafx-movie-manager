JAVAC = javac
JAVA = java
SRC_DIR = src
OUT_DIR = bin
DOC_DIR = doc
PATH_TO_FX = javafx-sdk-21.0.3/lib
FX_MODULES = javafx.controls,javafx.fxml
SOURCES = $(shell find $(SRC_DIR) -name "*.java")
MAIN_CLASS = ui.MainInterface

all: compile copy_resources

compile:
	@echo "Compilation des sources..."
	@mkdir -p $(OUT_DIR)
	$(JAVAC) -d $(OUT_DIR) --module-path $(PATH_TO_FX) --add-modules $(FX_MODULES) -cp "lib/*" $(SOURCES)

copy_resources:
	@echo "Copie des FXML..."
	@mkdir -p $(OUT_DIR)/ui
	cp $(SRC_DIR)/ui/*.fxml $(OUT_DIR)/ui/ || true

run: all
	@echo "Lancement de l'application..."
	$(JAVA) --module-path $(PATH_TO_FX) --add-modules $(FX_MODULES) -cp "$(OUT_DIR):lib/*" $(MAIN_CLASS)

test: all
	@echo "Lancement des tests JUnit..."
	$(JAVA) --module-path $(PATH_TO_FX) --add-modules $(FX_MODULES) -cp "$(OUT_DIR):lib/*" org.junit.platform.console.ConsoleLauncher --scan-classpath

javadoc:
	@echo "Génération de la Javadoc..."
	@mkdir -p $(DOC_DIR)
	javadoc -d $(DOC_DIR) --module-path $(PATH_TO_FX) --add-modules $(FX_MODULES) -cp "lib/*" -encoding UTF-8 -Xdoclint:none $(SOURCES)
clean:
	rm -rf $(OUT_DIR) $(DOC_DIR)

.PHONY: all compile copy_resources run test javadoc clean