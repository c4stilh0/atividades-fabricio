import java.util.Scanner;

// Classe Node
class Node<T> {
    private T value;
    private Node<T> next;

    public Node(T value) {
        this.value = value;
        this.next = null;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}

// Classe da Lista Encadeada
class UniBHList<T extends Comparable<T>> {
    private Node<T> firstNode;
    private int totalElements;

    public void insertAtBeginning(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.setNext(firstNode);
        firstNode = newNode;
        totalElements++;
    }

    public void insertAtEnd(T value) {
        Node<T> newNode = new Node<>(value);
        if (firstNode == null) {
            firstNode = newNode;
        } else {
            Node<T> current = firstNode;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        totalElements++;
    }

    public void insertInOrder(T value) {
        Node<T> newNode = new Node<>(value);
        if (firstNode == null || firstNode.getValue().compareTo(value) > 0) {
            newNode.setNext(firstNode);
            firstNode = newNode;
        } else {
            Node<T> current = firstNode;
            while (current.getNext() != null && current.getNext().getValue().compareTo(value) < 0) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
        totalElements++;
    }

    public Node<T> removeAtBeginning() {
        if (firstNode == null) return null;
        Node<T> aux = firstNode;
        firstNode = firstNode.getNext();
        totalElements--;
        return aux;
    }

    public Node<T> removeAtEnd() {
        if (firstNode == null) return null;
        if (firstNode.getNext() == null) {
            Node<T> aux = firstNode;
            firstNode = null;
            totalElements--;
            return aux;
        }

        Node<T> current = firstNode;
        while (current.getNext().getNext() != null) {
            current = current.getNext();
        }
        Node<T> aux = current.getNext();
        current.setNext(null);
        totalElements--;
        return aux;
    }

    public boolean removeByValue(T value) {
        if (firstNode == null) return false;
        if (firstNode.getValue().equals(value)) {
            firstNode = firstNode.getNext();
            totalElements--;
            return true;
        }

        Node<T> current = firstNode;
        while (current.getNext() != null && !current.getNext().getValue().equals(value)) {
            current = current.getNext();
        }

        if (current.getNext() == null) return false;
        current.setNext(current.getNext().getNext());
        totalElements--;
        return true;
    }

    public Node<T> search(T value) {
        Node<T> current = firstNode;
        while (current != null) {
            if (current.getValue().equals(value)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    @Override
    public String toString() {
        if (totalElements == 0) {
            return "[ ]";
        }

        Node<T> currentNode = firstNode;
        StringBuilder builder = new StringBuilder("[");
        
        while (currentNode != null) {
            builder.append(currentNode.getValue());
            if (currentNode.getNext() != null) {
                builder.append(", ");
            }
            currentNode = currentNode.getNext();
        }

        builder.append("]");
        return builder.toString();
    }
}

// Interface no Console
public class AtividadeAula2802 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UniBHList<Integer> list = new UniBHList<>();
        int option;

        do {
            System.out.println("\n=== Menu da Lista Encadeada ===");
            System.out.println("1 - Inserir no início");
            System.out.println("2 - Inserir no final");
            System.out.println("3 - Inserir em ordem");
            System.out.println("4 - Remover do início");
            System.out.println("5 - Remover do final");
            System.out.println("6 - Remover por valor");
            System.out.println("7 - Buscar elemento");
            System.out.println("8 - Exibir lista");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Digite um número para inserir no início: ");
                    int num1 = scanner.nextInt();
                    list.insertAtBeginning(num1);
                    System.out.println("Elemento adicionado! Lista atual: " + list);
                    break;

                case 2:
                    System.out.print("Digite um número para inserir no final: ");
                    int num2 = scanner.nextInt();
                    list.insertAtEnd(num2);
                    System.out.println("Elemento adicionado! Lista atual: " + list);
                    break;

                case 3:
                    System.out.print("Digite um número para inserir em ordem: ");
                    int num3 = scanner.nextInt();
                    list.insertInOrder(num3);
                    System.out.println("Elemento adicionado! Lista atual: " + list);
                    break;

                case 4:
                    if (list.removeAtBeginning() != null) {
                        System.out.println("Elemento removido do início! Lista atual: " + list);
                    } else {
                        System.out.println("A lista está vazia!");
                    }
                    break;

                case 5:
                    if (list.removeAtEnd() != null) {
                        System.out.println("Elemento removido do final! Lista atual: " + list);
                    } else {
                        System.out.println("A lista está vazia!");
                    }
                    break;

                case 6:
                    System.out.print("Digite o valor a ser removido: ");
                    int valueToRemove = scanner.nextInt();
                    if (list.removeByValue(valueToRemove)) {
                        System.out.println("Elemento removido! Lista atual: " + list);
                    } else {
                        System.out.println("Elemento não encontrado!");
                    }
                    break;

                case 7:
                    System.out.print("Digite o valor a ser buscado: ");
                    int searchValue = scanner.nextInt();
                    if (list.search(searchValue) != null) {
                        System.out.println("Elemento encontrado na lista!");
                    } else {
                        System.out.println("Elemento não encontrado!");
                    }
                    break;

                case 8:
                    System.out.println("Lista atual: " + list);
                    break;

                case 0:
                    System.out.println("Saindo do programa...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (option != 0);

        scanner.close();
    }
}
