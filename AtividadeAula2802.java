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

    public void setValue(T value) {
        this.value = value;
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

    public Node<T> removeAtBeginning() {
        if (firstNode == null) return null;
        Node<T> aux = firstNode;
        firstNode = firstNode.getNext();
        totalElements--;
        return aux;
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

    public void insertAfter(int index, T value) {
        if (index < 0 || index >= totalElements) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }
        Node<T> current = firstNode;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        Node<T> newNode = new Node<>(value);
        newNode.setNext(current.getNext());
        current.setNext(newNode);
        totalElements++;
    }

    public Node<T> removeAt(int index) {
        if (index < 0 || index >= totalElements) {
            throw new IndexOutOfBoundsException("Índice inválido");
        }
        if (index == 0) {
            return removeAtBeginning();
        }
        Node<T> current = firstNode;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        Node<T> removedNode = current.getNext();
        current.setNext(removedNode.getNext());
        totalElements--;
        return removedNode;
    }

    public int size() {
        return totalElements;
    }

    public void updateElement(T oldValue, T newValue) {
        Node<T> current = firstNode;
        while (current != null) {
            if (current.getValue().equals(oldValue)) {
                current.setValue(newValue);
                return;
            }
            current = current.getNext();
        }
        throw new IllegalArgumentException("Elemento não encontrado na lista");
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
            System.out.println("\n=== Menu da Lista ===");
            System.out.println("1 - Inserir no início");
            System.out.println("2 - Inserir no final");
            System.out.println("3 - Inserir após índice");
            System.out.println("4 - Remover por índice");
            System.out.println("5 - Retornar tamanho da lista");
            System.out.println("6 - Modificar elemento");
            System.out.println("7 - Exibir lista");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            
            option = scanner.nextInt();

            try {
                switch (option) {
                    case 1:
                        System.out.print("Digite um número para inserir no início: ");
                        int num1 = scanner.nextInt();
                        list.insertAtBeginning(num1);
                        break;

                    case 2:
                        System.out.print("Digite um número para inserir no final: ");
                        int num2 = scanner.nextInt();
                        list.insertAtEnd(num2);
                        break;
                    
                    case 3:
                        System.out.print("Digite o índice onde deseja inserir: ");
                        int index = scanner.nextInt();
                        System.out.print("Digite o valor: ");
                        int value = scanner.nextInt();
                        list.insertAfter(index, value);
                        break;
                    
                    case 4:
                        System.out.print("Digite o índice do elemento a ser removido: ");
                        int removeIndex = scanner.nextInt();
                        list.removeAt(removeIndex);
                        break;
                    
                    case 5:
                        System.out.println("Tamanho da lista: " + list.size());
                        break;
                    
                    case 6:
                        System.out.print("Digite o valor a ser modificado: ");
                        int oldValue = scanner.nextInt();
                        System.out.print("Digite o novo valor: ");
                        int newValue = scanner.nextInt();
                        list.updateElement(oldValue, newValue);
                        break;
                    
                    case 7:
                        System.out.println("Lista atual: " + list);
                        break;

                    case 0:
                        System.out.println("Saindo do programa...");
                        break;

                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (option != 0);

        scanner.close();
    }
}
