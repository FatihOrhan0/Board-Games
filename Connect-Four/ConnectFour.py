#this was originally intended to be a connect four with rotations. However, due to 
#limited timeline rotation part was not implemented. 

from PyQt5.QtWidgets import *
from PyQt5.QtGui import *
from PyQt5.QtCore import *
import sys

global turn
turn = 1
board = [[0 for i in range(6)] for j in range(7)]

def play(k):
    global turn
    for i in range(6):
        if board[k - 1][i] == 0:
            if turn % 2 == 1:
                board[k - 1][i] = 1
                turn += 1
                return i + 1
            else:
                board[k - 1][i] = 2
                turn += 1
                return i + 1   
    return

def connected(col, row):
    b = False
    t = board[row - 1][col - 1]
    
    if t == 0:
        return False

    i = 1
    t1 = 0
    bound = [[col, row]]
    while (row + i) < len(board):
        if t == board[row - 1 + i][col - 1]:
            bound.append([col, row + i])
            t1 += 1
        elif t1 == 3:
            b = True
        else:
            break        
        i += 1
    
    if t1 == 3:
        b = True
    if b:
        game_over(t)
        return (b, bound)
    
    j = 1
    while (row - j) > 0:
        if t == board[row - 1 - j][col - 1]:
            t1 += 1
            bound.append([col, row - j])
        elif t1 == 3:
            b = True
        else:
            break        
        j += 1
    if t1 == 3:
        b = True
    if b:
        game_over(t)
        return (b, bound)
        
    k = 1
    t2 = 0
    bound = [[col, row]]
    while (col + k) < len(board[0]):
        if t == board[row - 1][col - 1 + k]:
            bound.append([col + k, row])
            t2 += 1
        elif t2 == 3:
            b = True
        else:
            break        
        k += 1
    if t2 == 3:
        b = True
    if b:
        game_over(t)
        return (b, bound)
    
    l = 1
    while (col - l) > 0:
        if t == board[row - 1][col - 1 - l]:
            t2 += 1  
            bound.append([col - l, row])
        elif t2 == 3:
            b = True
        else:
            break        
        l += 1
    if t2 == 3:
        b = True
    if b:
        game_over(t)
        return (b, bound)

    m = 1
    t3 = 0 
    bound = [[col, row]]
    while (col + m) < len(board) and (row + m) < len(board[0]):
        if t == board[row - 1 + m][col - 1 + m]:
            t3 += 1
            bound.append([col + m, row + m])
        elif t3 == 3:
            b = True
        else:
            break       
        m += 1
    if t3 == 3:
        b = True
    if b:
        game_over(t)
        return (b, bound)
    
    n = 1
    while (col - n) > 0 and (row - n) > 0:
        if t == board[row - 1 - n][col - 1 - n]:
            t3 += 1  
            bound.append([col - n, row - n])
        elif t3 == 3:
            b = True
        else:
            break        
        n += 1
    if t3 == 3:
        b = True
    if b:
        game_over(t)
        return (b, bound)
        
    o = 1
    t4 = 0 
    bound = [[col, row]]
    while (col + o) < len(board) and (row + o) > 0:
        if t == board[col - 1 + o][row - 1 - o]:
            t4 += 1
            bound.append([col + o, row - o])
        else:
            break        
        o += 1
    if t4 == 3:
        b = True
    if b:
        game_over(t)
        return (b, bound)
        
    p = 1
    while (col - p) > 0 and (row + p) < len(board):
        if t == board[col - 1 - p][row - 1 + p]:
            t4 += 1  
            bound.append([col - p, row + p])
        else:
            break        
        p += 1
    if t4 == 3:
        b = True
    if b:
        game_over(t)
        return (b, bound)
    
    return (b, [])

def game_over(player):
    
    if player == 1:
        print("Game over! Red player wins.")
    else:
        print("Game over! Yellow player wins.")
        
def finder(row, col):
    return 41 - row * 7 + col
        
        
class Window(QMainWindow):
    def __init__(self):
        super().__init__()
        self.msg = QMessageBox()
        self.setWindowTitle("Connect 4 ")
        self.setGeometry(610, 240, 700, 600)
        self.buttons = []
        self.UiComponents()
        self.show()
        self.p1color = "Red"
        self.p2color = "Yellow"

    def UiComponents(self):
        for i in range(6):
            for j in range(7):
                name = "b" + str(i) + str(j)
                button = QPushButton('', self)
                button.setGeometry(j*100, i*100, 100, 100)
                button.setStyleSheet("border-radius : 50; border: 3px solid black")
                button.clicked.connect(self.clickme)
                self.buttons.append(button)

    def clickme(self):
        global turn
        ind = self.buttons.index(self.sender())
        col = (ind) % 7 + 1
        row = 6 - (int((ind + 1 - col) / 7))
        r = play(col)
        
        if r == None:
            print()
        else:
            btp = 42 - ((r - 1) * 7 + (8 - col))
            color = "white"
            if turn % 2 == 1:
                color = self.p2color
            else:
                color = self.p1color
            self.buttons[btp].setStyleSheet("border-radius : 50; border: 2px solid black; background: " + color)
            res = connected(r, col)
            con = res[0]
            bo = res[1]
            if con:
                for r, c in bo:
                     k = finder(r, c)
                     self.buttons[k].setStyleSheet("border-radius : 50; border: 2px solid black; background: dark blue")
                self.msg.setText("Game over " + color + " player wins.")
                self.msg.exec_()
                for b in self.buttons:
                    b.setEnabled(False)

App = QApplication(sys.argv)
window = Window()
sys.exit(App.exec())
        
